/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.di.module;

import com.android.nishant.flickr101.data.retrofit.FlickrService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Application module which provides dependencies to application level
 */
@Module
public class AppModule {

    private static final String BASE_URL = "https://api.flickr.com/services/rest/";

    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    @Singleton
    @Provides
    FlickrService provideFlickrService(Retrofit retrofit) {
        return retrofit.create(FlickrService.class);
    }
}
