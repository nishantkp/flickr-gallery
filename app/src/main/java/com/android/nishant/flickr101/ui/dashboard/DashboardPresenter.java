/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.dashboard;

import android.text.TextUtils;

import com.android.nishant.flickr101.base.BasePresenter;
import com.android.nishant.flickr101.data.callback.OnTaskCompletion;
import com.android.nishant.flickr101.data.manager.DataManager;
import com.android.nishant.flickr101.ui.model.Photo;

import java.util.List;

/**
 * Presenter that deals with getting data from user search query
 */
public class DashboardPresenter
        extends BasePresenter<DashboardContract.View>
        implements DashboardContract.Presenter {

    private DataManager mDataManager;

    DashboardPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public DashboardContract.View getView() {
        return super.getView();
    }

    @Override
    public void attachView(DashboardContract.View view) {
        super.attachView(view);
    }

    @Override
    public void getPhotos(String userQuery) {

        // If user has entered empty string exit the method
        if (TextUtils.isEmpty(userQuery)) return;

        getView().showProgressDialog();

        mDataManager.getUserSearchResponse(userQuery, new OnTaskCompletion() {
            @Override
            public void onData(List<Photo> data) {
                getView().cancelProgressDialog();
                getView().onData(data);
            }

            @Override
            public void onError(String message) {
                getView().cancelProgressDialog();
                getView().onError(message);
            }
        });
    }
}
