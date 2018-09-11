/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.di.component;

import com.android.nishant.flickr101.application.FlickrApp;
import com.android.nishant.flickr101.di.module.ActivityModule;
import com.android.nishant.flickr101.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Application component for dagger
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityModule.class
})
public interface AppComponent {
    void inject(FlickrApp app);
}
