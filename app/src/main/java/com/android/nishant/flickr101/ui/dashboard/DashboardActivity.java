/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.nishant.flickr101.databinding.ActivityDashboardBinding;
import com.android.nishant.flickr101.R;
import com.android.nishant.flickr101.data.manager.DataManager;
import com.android.nishant.flickr101.ui.adapter.PhotoAdapter;
import com.android.nishant.flickr101.ui.model.PhotoDetail;
import com.android.nishant.flickr101.ui.model.UserSearchQuery;

import java.util.List;

public class DashboardActivity
        extends AppCompatActivity
        implements DashboardContract.View {

    private static DashboardPresenter mPresenter;
    private ActivityDashboardBinding mBinding;
    private PhotoAdapter mPhotoAdapter;

    @BindingAdapter({"app:performSearchPerQuery"})
    public static void searchPerQuery(EditText view, String query) {
        mPresenter.getPhotos(query);
    }

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
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        mBinding.setQuery(new UserSearchQuery());

        mPhotoAdapter = new PhotoAdapter();
        mBinding.dashboardRv.setAdapter(mPhotoAdapter);

        mPresenter = new DashboardPresenter(DataManager.getInstance());
        mPresenter.attachView(this);

        // set layout manager on recycler view
        mBinding.dashboardRv.setLayoutManager(new LinearLayoutManager(this));

        // Add divider between two items
        DividerItemDecoration itemDecor =
                new DividerItemDecoration(mBinding.dashboardRv.getContext(), DividerItemDecoration.VERTICAL);
        mBinding.dashboardRv.addItemDecoration(itemDecor);

        noDataAvailable();
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
