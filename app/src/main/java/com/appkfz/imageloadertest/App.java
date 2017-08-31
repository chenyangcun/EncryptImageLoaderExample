package com.appkfz.imageloadertest;

import android.app.Application;

import com.appkfz.imageloadertest.picasso.CustomRequestHandler;
import com.squareup.picasso.Picasso;

/**
 * Created by chenyc on 17/8/31.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso picasso = new Picasso.Builder(this).addRequestHandler(new CustomRequestHandler()).build();
        Picasso.setSingletonInstance(picasso);
    }
}
