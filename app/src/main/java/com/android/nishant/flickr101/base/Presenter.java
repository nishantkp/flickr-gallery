/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.base;

public interface Presenter<T extends MvpView> {
    void attachView(T view);

    void detachView();
}
