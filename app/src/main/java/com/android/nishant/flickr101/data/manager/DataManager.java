/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.data.manager;

import android.support.annotation.NonNull;

import com.android.nishant.flickr101.data.callback.OnTaskCompletion;
import com.android.nishant.flickr101.data.usecase.FlickrUseCase;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * DataManager class
 * Use this class to perform all the business logic operations
 */
@Singleton
public class DataManager implements DataManagerContract {

    private FlickrUseCase mFlickrUseCase;

    @Inject
    DataManager(FlickrUseCase flickrUseCase) {
        mFlickrUseCase = flickrUseCase;
    }

    /**
     * Call this method to get the list of photos from user entered query
     *
     * @param userQuery user search query
     * @param callback  TaskCompletion callback for data and error
     */
    @Override
    public void getUserSearchResponse(@NonNull String userQuery, @NonNull OnTaskCompletion callback) {
        mFlickrUseCase.getUserSearchResponse(userQuery, callback);
    }
}
