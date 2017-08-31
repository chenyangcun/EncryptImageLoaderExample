package com.appkfz.imageloadertest.glide;

import com.appkfz.imageloadertest.EncryptUtil;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Fetches an {@link InputStream} using the okhttp library.
 */
public class OkHttpStreamFetcher implements DataFetcher<InputStream> {
    private final OkHttpClient client;
    private final CustomGlideUrl url;
    private InputStream stream;
    private ResponseBody responseBody;

    private final static int MAX_TRY = 3;

    private int tryTime = 0;

    public OkHttpStreamFetcher(OkHttpClient client, CustomGlideUrl url) {
        this.client = client;
        this.url = url;
    }

    @Override
    public InputStream loadData(Priority priority) throws Exception {
        ResponseBody responseBody = null;

        responseBody = getResponse(priority);
        /*do {
            try {
                responseBody = getResponse(priority);
            } catch (Exception e) {
                tryTime++;
                L.d("load url tryTime:" + tryTime + "\n " + url.toStringUrl());
                if (tryTime >= MAX_TRY) {
                    throw new Exception("load image exceed max retry times");
                } else {
//                    Thread.sleep(500);
                }
            }
        } while (responseBody == null);*/

        long contentLength = responseBody.contentLength();
        byte[] encryptData = responseBody.bytes();
        try {
            byte[] rawData =  EncryptUtil.decrypt(encryptData);
            return new ByteArrayInputStream(rawData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private ResponseBody getResponse(Priority priority) throws Exception {
        Request.Builder requestBuilder = new Request.Builder()
                .url(url.toStringUrl());

        for (Map.Entry<String, String> headerEntry : url.getHeaders().entrySet()) {
            String key = headerEntry.getKey();
            requestBuilder.addHeader(key, headerEntry.getValue());
        }

        Request request = requestBuilder.build();

        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();
        if (!response.isSuccessful()) {
            throw new IOException("Request failed with code: " + response.code());
        }
        return responseBody;
    }

    @Override
    public void cleanup() {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                // Ignored
            }
        }
        if (responseBody != null) {
            try {
                responseBody.close();
            } catch (Exception e) {
                // Ignored.
            }
        }
    }

    @Override
    public String getId() {
        return url.getCacheKey();
    }

    @Override
    public void cancel() {
        // TODO: call cancel on the client when this method is called on a background thread. See #257
    }
}
