/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.dashboard;

import com.android.nishant.flickr101.base.MvpView;
import com.android.nishant.flickr101.ui.model.PhotoDetail;

import java.util.List;

public interface DashboardContract {
    interface View extends MvpView {
        void showProgressDialog();

        void cancelProgressDialog();

        void onData(List<PhotoDetail> photoDetails);

        void onError(String message);
    }

    interface Presenter {
        void getPhotos(String userQuery);
    }
}
