/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.data.retrofit;

import com.android.nishant.flickr101.ui.model.FlickrObject;
import com.android.nishant.flickr101.ui.model.FlickrSizeQuery;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API for retrofit
 */
public interface FlickrService {
    /**
     * Retrofit API for GET method.
     * This method appends search query to Flickr API endpoint
     *
     * @param searchQuery search query given by user
     * @return FlickrObject which contains a list of photos based on user search query
     */
    @GET("?api_key=949e98778755d1982f537d56236bbb42&method=flickr.photos.search&format=json&nojsoncallback=1")
    Observable<FlickrObject> getDataFromQuery(@Query("text") String searchQuery);

    /**
     * Retrofit API for GET method
     * This method appends photoId to flicker end point ti get all available photo sizes
     *
     * @param photoId photo idphoto
     * @return FlickrSizeQuery object which contains all the available photo sizes for particular id
     */
    @GET("?api_key=949e98778755d1982f537d56236bbb42&method=flickr.photos.getSizes&format=json&nojsoncallback=1")
    Observable<FlickrSizeQuery> getImageSizes(@Query("photo_id") String photoId);
}
