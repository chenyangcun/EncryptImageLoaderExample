package com.appkfz.imageloadertest.glide;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;

import java.net.URL;

/**
 * Created by Huan on 2016/1/20.
 */
public class CustomGlideUrl extends GlideUrl {

    public CustomGlideUrl(URL url) {
        super(url);
    }

    public CustomGlideUrl(String url) {
        super(url);
    }

    public CustomGlideUrl(URL url, Headers headers) {
        super(url, headers);
    }

    public CustomGlideUrl(String url, Headers headers) {
        super(url, headers);
    }


}
