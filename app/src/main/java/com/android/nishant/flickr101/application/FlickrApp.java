/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.application;

import android.app.Activity;
import android.app.Application;

import com.android.nishant.flickr101.data.manager.DataManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Application class
 */
public class FlickrApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mActivityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize dataManager when app starts
        DataManager.getInstance();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityDispatchingAndroidInjector;
    }
}
