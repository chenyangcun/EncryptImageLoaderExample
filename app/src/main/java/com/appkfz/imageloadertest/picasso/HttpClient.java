package com.appkfz.imageloadertest.picasso;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/**
 * Created by chenyc on 17/8/31.
 */

public class HttpClient {

    private static OkHttpClient client;

    public static OkHttpClient getDefaultOkHttpClient() {
        if(client == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectionPool(new ConnectionPool(0, 5, TimeUnit.MINUTES));
            client = builder.build();
        }
        return client;
    }
}
