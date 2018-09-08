/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.nishant.flickr101.R;
import com.android.nishant.flickr101.data.manager.DataManager;
import com.android.nishant.flickr101.ui.model.Photo;

import java.util.List;

public class DashboardActivity
        extends AppCompatActivity
        implements DashboardContract.View {

    private DashboardPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mPresenter = new DashboardPresenter(DataManager.getInstance());
        mPresenter.attachView(this);

        // Fake query
        mPresenter.getPhotos("animal");
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void cancelProgressDialog() {

    }

    @Override
    public void onData(List<Photo> photos) {
        for (Photo photo : photos) {
            Log.i("Data", photo.getTitle());
        }
    }

    @Override
    public void onError(String message) {
        Log.i("Error", message);
    }
}
