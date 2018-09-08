/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.dashboard;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.nishant.flickr101.databinding.ActivityDashboardBinding;
import com.android.nishant.flickr101.R;
import com.android.nishant.flickr101.data.manager.DataManager;
import com.android.nishant.flickr101.ui.model.PhotoDetail;

import java.util.List;

public class DashboardActivity
        extends AppCompatActivity
        implements DashboardContract.View {

    private DashboardPresenter mPresenter;
    private ActivityDashboardBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        mPresenter = new DashboardPresenter(DataManager.getInstance());
        mPresenter.attachView(this);

        // Fake query
        mPresenter.getPhotos("bird");
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void cancelProgressDialog() {

    }

    @Override
    public void onData(List<PhotoDetail> photoDetails) {
        for (PhotoDetail photo : photoDetails) {
            Log.i("Data", "\nTitle : " + photo.getTitle() + " Url : " + photo.getUrl());
        }
    }

    @Override
    public void onError(String message) {
        Log.i("Error", message);
    }
}
