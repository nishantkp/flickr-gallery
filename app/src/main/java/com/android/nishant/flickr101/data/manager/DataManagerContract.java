/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.data.manager;

import android.support.annotation.NonNull;

import com.android.nishant.flickr101.data.callback.OnTaskCompletion;

/**
 * DataManager contract
 */
public interface DataManagerContract {

    void getUserSearchResponse(@NonNull String userQuery,
                               @NonNull final OnTaskCompletion callback);
}
