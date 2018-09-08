/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.dashboard;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.android.nishant.flickr101.databinding.ActivityDashboardBinding;
import com.android.nishant.flickr101.R;
import com.android.nishant.flickr101.data.manager.DataManager;
import com.android.nishant.flickr101.ui.adapter.PhotoAdapter;
import com.android.nishant.flickr101.ui.model.PhotoDetail;

import java.util.List;

public class DashboardActivity
        extends AppCompatActivity
        implements DashboardContract.View {

    private DashboardPresenter mPresenter;
    private ActivityDashboardBinding mBinding;
    private PhotoAdapter mPhotoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        mPresenter = new DashboardPresenter(DataManager.getInstance());
        mPresenter.attachView(this);

        mPhotoAdapter = new PhotoAdapter();
        mBinding.dashboardRv.setAdapter(mPhotoAdapter);

        // set layout manager on recycler view
        mBinding.dashboardRv.setLayoutManager(new LinearLayoutManager(this));

        // Add divider between two items
        DividerItemDecoration itemDecor =
                new DividerItemDecoration(mBinding.dashboardRv.getContext(), DividerItemDecoration.VERTICAL);
        mBinding.dashboardRv.addItemDecoration(itemDecor);

        // Fake query
        mPresenter.getPhotos("bird");
    }

    @Override
    public void showProgressDialog() {
        mBinding.dashboardProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void cancelProgressDialog() {
        mBinding.dashboardProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onData(List<PhotoDetail> photoDetails) {
        mPhotoAdapter.updateDataSet(photoDetails);
    }

    @Override
    public void onError(String message) {
        Log.i("Error", message);
    }
}
