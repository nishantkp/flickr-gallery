/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.data.usecase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.nishant.flickr101.data.callback.OnTaskCompletion;
import com.android.nishant.flickr101.data.retrofit.ApiUtils;
import com.android.nishant.flickr101.ui.model.FlickrObject;
import com.android.nishant.flickr101.ui.model.FlickrSizeQuery;
import com.android.nishant.flickr101.ui.model.Photo;
import com.android.nishant.flickr101.ui.model.PhotoDetail;
import com.android.nishant.flickr101.ui.model.Size;
import com.android.nishant.flickr101.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public final class FlickrUseCase {
    private List<PhotoDetail> photoDetails;

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
        ApiUtils.getFlickerService().getDataFromQuery(userQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<FlickrObject, ObservableSource<List<PhotoDetail>>>() {
                    @Override
                    public ObservableSource<List<PhotoDetail>> apply(FlickrObject flickrObject) {
                        return getPhotoList(flickrObject.getPhotos().getPhoto());
                    }
                })
                .subscribe(new Observer<List<PhotoDetail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Empty for now
                    }

                    @Override
                    public void onNext(List<PhotoDetail> details) {
                        photoDetails = new ArrayList<>();
                        photoDetails.addAll(details);
                        callback.onData(details);
                        getPhotoSizes(details);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // Empty for now
                    }
                });
    }

    /**
     * Call this method to get size of image like width, height and actual byte size
     *
     * @param details List of {@link PhotoDetail} objects containing photoId, title, and image url
     */
    private void getPhotoSizes(List<PhotoDetail> details) {
        Observable.just(details).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(new Function<List<PhotoDetail>, Iterable<PhotoDetail>>() {
                    @Override
                    public Iterable<PhotoDetail> apply(List<PhotoDetail> details) {
                        return details;
                    }
                })
                .flatMap(new Function<PhotoDetail, ObservableSource<FlickrSizeQuery>>() {
                    @Override
                    public ObservableSource<FlickrSizeQuery> apply(PhotoDetail photoDetail) {
                        return ApiUtils.getFlickerService().getImageSizes(photoDetail.getId())
                                .subscribeOn(Schedulers.io());
                    }
                })
                .flatMap(new Function<FlickrSizeQuery, ObservableSource<PhotoDetail>>() {
                    @Override
                    public ObservableSource<PhotoDetail> apply(FlickrSizeQuery flickrSizeQuery) {
                        return getSize(flickrSizeQuery);
                    }
                })
                .toList()
                .subscribe(new SingleObserver<List<PhotoDetail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Empty for now
                    }

                    @Override
                    public void onSuccess(List<PhotoDetail> details) {
                        for (PhotoDetail detail : details) {
                            if (photoDetails.contains(detail)) {
                                int index = photoDetails.indexOf(detail);
                                PhotoDetail object = photoDetails.get(index);
                                object.setHeight(detail.getHeight());
                                object.setWidth(detail.getWidth());
                                object.setByteSize(detail.getByteSize());
                                object.setWidthByHeight(detail.getWidthByHeight());
                                photoDetails.set(index, object);
                            }
                            Log.i("info", "\nid : " + detail.getId()
                                    + " width: " + detail.getWidth() + " height: " + detail.getHeight()
                                    + " original url: " + detail.getOriginalUrl());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    /**
     * Call this method to get the the observable object of {@link PhotoDetail} which contains
     * photo width, height, original image url, byte size
     * <p>
     * NOTE : Observable must be subscribed on {@link Schedulers}.io()
     *
     * @param flickrSizeQuery {@link FlickrSizeQuery} object contains all the available sizes of a
     *                        particular image
     * @return observable source which is subscribed on {@link Schedulers}.io()
     */
    private ObservableSource<PhotoDetail> getSize(final FlickrSizeQuery flickrSizeQuery) {
        return Observable.create(new ObservableOnSubscribe<PhotoDetail>() {
            @Override
            public void subscribe(ObservableEmitter<PhotoDetail> emitter) {
                List<Size> sizeList = flickrSizeQuery.getSizes().getSize();
                for (Size size : sizeList) {
                    // Original image details can be distinguished by "Original" label
                    if (size.getLabel().equals("Original")) {
                        String height = size.getHeight();
                        String width = size.getWidth();
                        String originalUrl = size.getSource();
                        String byteSize = NetworkUtils.getByteSizeFromUrl(originalUrl);
                        String widthByHeight = width + "W x " + height + "H";
                        emitter.onNext(
                                new PhotoDetail(
                                        NetworkUtils.getImageIdFromFlickrUrl(originalUrl),
                                        width,
                                        height,
                                        widthByHeight,
                                        originalUrl,
                                        byteSize));
                    }
                }
                // After every item is emitted must call onComplete() on emitter
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * Call this method to get the observable object of List of {@link PhotoDetail}
     * which contains photo url, and title
     *
     * @param photoList list if {@link Photo} containing photoId, secretId, serverId
     * @return Observable object of list of {@link PhotoDetail}
     */
    private Observable<List<PhotoDetail>> getPhotoList(List<Photo> photoList) {
        List<PhotoDetail> photoDetailList = new ArrayList<>();
        for (Photo photo : photoList) {
            String photoUrl =
                    NetworkUtils.generatePhotoUrl(
                            String.valueOf(photo.getFarm()),
                            photo.getServer(),
                            photo.getId(),
                            photo.getSecret());
            photoDetailList.add(new PhotoDetail(photo.getTitle(), photoUrl, photo.getId()));
        }
        return Observable.just(photoDetailList);
    }
}
