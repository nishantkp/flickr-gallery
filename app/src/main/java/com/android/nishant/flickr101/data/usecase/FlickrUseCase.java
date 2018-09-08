/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.data.usecase;

import android.support.annotation.NonNull;

import com.android.nishant.flickr101.data.callback.OnTaskCompletion;
import com.android.nishant.flickr101.data.retrofit.ApiUtils;
import com.android.nishant.flickr101.ui.model.FlickrObject;
import com.android.nishant.flickr101.ui.model.Photo;
import com.android.nishant.flickr101.ui.model.PhotoDetail;

import java.util.ArrayList;
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
                .enqueue(new Callback<FlickrObject>() {
                    @Override
                    public void onResponse(@NonNull Call<FlickrObject> call,
                                           @NonNull Response<FlickrObject> response) {
                        FlickrObject object = response.body();
                        if (object != null) {
                            callback.onData(getPhotoDetailList(object.getPhotos().getPhoto()));
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

    /**
     * Call this method to get the list of PhotoData which contains title and photo url
     *
     * @param photoList list of photos from Flickr endpoint
     * @return list of {@link PhotoDetail}
     */
    private List<PhotoDetail> getPhotoDetailList(List<Photo> photoList) {
        List<PhotoDetail> photoDetailList = new ArrayList<>();
        for (Photo photo : photoList) {
            String photoUrl = generatePhotoUrl(
                    String.valueOf(photo.getFarm()),
                    photo.getServer(),
                    photo.getId(),
                    photo.getSecret());
            photoDetailList.add(new PhotoDetail(photo.getTitle(), photoUrl));
        }
        return photoDetailList;
    }


    /**
     * Call this method to generate photo URL from fields in Flicker endpoint
     * <p>
     * Generated url will be of form,
     * "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg"
     *
     * @param farmId   farm id from Flickr endpoint
     * @param serverId Server id from Flickr endpoint
     * @param id       id from Flickr endpoint
     * @param secret   secret key from Flickr endpoint
     * @return Url string for photo
     */
    private String generatePhotoUrl(String farmId, String serverId, String id, String secret) {
        return "https://farm" + farmId + ".staticflickr.com/" + serverId +
                "/" + id + "_" + secret + ".jpg";
    }
}
