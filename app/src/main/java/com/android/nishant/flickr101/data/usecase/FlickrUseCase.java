/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.data.usecase;

import android.support.annotation.NonNull;

import com.android.nishant.flickr101.data.callback.OnTaskCompletion;
import com.android.nishant.flickr101.data.retrofit.ApiUtils;
import com.android.nishant.flickr101.ui.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class FlickrUseCase {

    public FlickrUseCase() {
    }

    /**
     * Call this method to get the list of photos from user query
     *
     * @param userQuery query entered by user
     * @param callback  task completion callback for data and error
     */
    public void getUserSearchResponse(@NonNull String userQuery,
                                      @NonNull final OnTaskCompletion callback) {
        ApiUtils.getFlickerService()
                .getDataFromQuery(userQuery)
                .enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {
                        callback.onData(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }
}
