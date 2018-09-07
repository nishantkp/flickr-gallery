/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.data.retrofit;

/**
 * Utility class for {@link RetrofitClient} and FlickrApi
 */
public class ApiUtils {

    /**
     * Call this method to get instance of {@link RetrofitClient} to perform network operation
     * on Flickr Url
     *
     * @return instance of {@link RetrofitClient} with {@link FlickrService}
     */
    public static FlickrService getFlickerService() {
        return RetrofitClient.getClient().create(FlickrService.class);
    }
}
