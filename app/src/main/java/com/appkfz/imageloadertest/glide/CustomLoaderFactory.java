package com.appkfz.imageloadertest.glide;

import android.content.Context;

import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/**
 * Created by Huan on 2016/1/20.
 */
public class CustomLoaderFactory implements ModelLoaderFactory<CustomGlideUrl, InputStream> {
    private OkHttpClient client;


    private OkHttpClient getClient() {
        if (client == null) {
            synchronized (CustomLoaderFactory.class) {
                OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
                builder.connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .connectionPool(new ConnectionPool(0, 5, TimeUnit.MINUTES));
                client = builder.build();
            }
        }
        return client;
    }

    @Override
    public ModelLoader<CustomGlideUrl, InputStream> build(Context context, GenericLoaderFactory factories) {
        return new OkHttpUrlLoader(getClient());
    }

    @Override
    public void teardown() {

    }
}
