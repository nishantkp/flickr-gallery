/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.dashboard;

import com.android.nishant.flickr101.base.MvpView;

public interface DashboardContract {
    interface View extends MvpView {

    }

    interface Presenter {
        void getPhotos(String userQuery);
    }
}
