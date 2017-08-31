package com.appkfz.imageloadertest.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.appkfz.imageloadertest.EncryptUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.RequestHandler;

import java.io.IOException;

import okhttp3.OkHttpClient;


/**
 * Created by chenyc on 17/8/31.
 */

public class CustomRequestHandler extends RequestHandler {

    private final static String SCHEME = "encrypt_http";


    @Override
    public boolean canHandleRequest(com.squareup.picasso.Request data) {
        String scheme = data.uri.getScheme();
        return SCHEME.equals(scheme);
    }


    @Override
    public Result load(Request request, int networkPolicy) throws IOException {
        String url = request.uri.toString().toUpperCase();
        url = url.substring("ENCRYPT_".length(), url.length());
        OkHttpClient okHttpClient = HttpClient.getDefaultOkHttpClient();
        okhttp3.Request req = new okhttp3.Request.Builder().url(url).build();
        okhttp3.Response response = okHttpClient.newCall(req).execute();
        byte[] encryptData = response.body().bytes();
        try {
           byte[] rawData =  EncryptUtil.decrypt(encryptData);
            Bitmap bitmap = BitmapFactory.decodeByteArray(rawData, 0, rawData.length);
            return new Result(bitmap, Picasso.LoadedFrom.NETWORK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
