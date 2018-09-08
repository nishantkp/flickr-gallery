/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.data.manager;

import android.support.annotation.NonNull;

import com.android.nishant.flickr101.data.callback.OnTaskCompletion;
import com.android.nishant.flickr101.data.usecase.FlickrUseCase;

/**
 * DataManager class
 * Use this class to perform all the business logic operations
 */
public class DataManager implements DataManagerContract {

    private FlickrUseCase mFlickrUseCase;

    /**
     * Private constructor so no one can make an object of DataManager
     */
    private DataManager() {
        mFlickrUseCase = new FlickrUseCase();
    }

    /**
     * Call this method to get instance of a DataManager
     *
     * @return DataManager instance
     */
    public static DataManager getInstance() {
        return LazyHolder.INSTANCE;
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

    /**
     * Lazy Singleton pattern
     */
    private static class LazyHolder {
        static final DataManager INSTANCE = new DataManager();
    }
}
