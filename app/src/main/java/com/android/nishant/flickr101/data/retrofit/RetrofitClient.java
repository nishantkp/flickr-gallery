/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.data.retrofit;

import retrofit2.Retrofit;

/**
 * Retrofit client SingleTon for network operation
 */
public class RetrofitClient {
    private static Retrofit sRetrofit = null;
    private static final String BASE_URL = "https://api.flickr.com/services/rest/";

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .addConverterFactory(FlickrConverter.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return sRetrofit;
    }
}
