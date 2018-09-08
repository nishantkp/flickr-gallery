/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.data.usecase;

import android.support.annotation.NonNull;

import com.android.nishant.flickr101.data.callback.OnTaskCompletion;
import com.android.nishant.flickr101.data.retrofit.ApiUtils;
import com.android.nishant.flickr101.ui.model.FlickrObject;

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
                .enqueue(new Callback<FlickrObject>() {
                    @Override
                    public void onResponse(@NonNull Call<FlickrObject> call,
                                           @NonNull Response<FlickrObject> response) {
                        FlickrObject object = response.body();
                        if (object != null) {
                            callback.onData(object.getPhotos().getPhoto());
                        } else {
                            callback.onError("No response from Flickr endpoint");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<FlickrObject> call, @NonNull Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }
}
