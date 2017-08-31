package com.appkfz.imageloadertest;

import android.app.Application;

import com.appkfz.imageloadertest.glide.CustomGlideUrl;
import com.appkfz.imageloadertest.glide.CustomLoaderFactory;
import com.appkfz.imageloadertest.picasso.CustomRequestHandler;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

/**
 * Created by chenyc on 17/8/31.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //注册Picasso特殊URL处理
        Picasso picasso = new Picasso.Builder(this).addRequestHandler(new CustomRequestHandler()).build();
        Picasso.setSingletonInstance(picasso);

        //注册Glide特殊URL处理
        Glide.get(this).register(CustomGlideUrl.class, InputStream.class, new CustomLoaderFactory());
    }
}
