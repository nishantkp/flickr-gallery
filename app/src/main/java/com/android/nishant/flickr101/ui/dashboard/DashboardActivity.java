/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.nishant.flickr101.databinding.ActivityDashboardBinding;
import com.android.nishant.flickr101.R;
import com.android.nishant.flickr101.ui.adapter.PhotoAdapter;
import com.android.nishant.flickr101.ui.model.PhotoDetail;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Activity responsible for displaying list of photos
 */
public class DashboardActivity
        extends AppCompatActivity
        implements DashboardContract.View {

    private ActivityDashboardBinding mBinding;
    @Inject
    DashboardPresenter mPresenter;
    @Inject
    PhotoAdapter mPhotoAdapter;
    @Inject
    LinearLayoutManager mLinearLayoutManager;
    @Inject
    DividerItemDecoration mDecoration;
    @Inject
    TextView.OnEditorActionListener mEditorActionListener;

    /**
     * Call this method to get the intent to start {@link DashboardActivity}
     *
     * @param context context from which we want to start {@link DashboardActivity}
     * @return intent
     */
    public static Intent getStarterIntent(Context context) {
        return new Intent(context, DashboardActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        mBinding.dashboardRv.setAdapter(mPhotoAdapter);

        // set layout manager on recycler view
        mBinding.dashboardRv.setLayoutManager(mLinearLayoutManager);

        // Add divider item
        mBinding.dashboardRv.addItemDecoration(mDecoration);

        noDataAvailable();
        mBinding.dashboardQueryTextView.setOnEditorActionListener(mEditorActionListener);
    }

    @Override
    public void showProgressDialog() {
        mBinding.dashboardEmptyView.setVisibility(View.GONE);
        mBinding.dashboardProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void cancelProgressDialog() {
        mBinding.dashboardProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void clearScreen() {
        mPhotoAdapter.clearData();
    }

    @Override
    public void noDataAvailable() {
        mBinding.dashboardEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onData(List<PhotoDetail> photoDetails) {
        mBinding.dashboardEmptyView.setVisibility(View.GONE);
        mPhotoAdapter.updateDataSet(photoDetails);
    }

    @Override
    public void onError(String message) {
        Log.i("Error", message);
    }
}
