/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.di.module;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.android.nishant.flickr101.data.manager.DataManager;
import com.android.nishant.flickr101.ui.adapter.PhotoAdapter;
import com.android.nishant.flickr101.ui.dashboard.DashboardActivity;
import com.android.nishant.flickr101.ui.dashboard.DashboardPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Dependencies for {@link DashboardActivity}
 */
@Module
public class DashboardActivityModule {

    @Provides
    DashboardPresenter providePresenter(DataManager dataManager, DashboardActivity activity) {
        DashboardPresenter presenter = new DashboardPresenter(dataManager);
        presenter.attachView(activity);
        return presenter;
    }

    @Provides
    PhotoAdapter providePhotoAdapter() {
        return new PhotoAdapter();
    }

    @Provides
    LinearLayoutManager provideLayoutManager(DashboardActivity dashboardActivity) {
        return new LinearLayoutManager(dashboardActivity);
    }

    @Provides
    DividerItemDecoration provideDividerItemDecoration(DashboardActivity dashboardActivity) {
        return new DividerItemDecoration(dashboardActivity, DividerItemDecoration.VERTICAL);
    }

    @Provides
    TextView.OnEditorActionListener provideListener(final DashboardPresenter presenter) {
        return new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null &&
                        (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    presenter.getPhotos(v.getText().toString());
                }
                return false;
            }
        };
    }
}
