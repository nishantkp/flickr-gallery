/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.application;

import android.app.Application;

import com.android.nishant.flickr101.data.manager.DataManager;

/**
 * Application class
 */
public class FlickrApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize dataManager when app starts
        DataManager.getInstance();
    }
}
