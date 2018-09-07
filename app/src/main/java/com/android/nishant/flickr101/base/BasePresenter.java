/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.base;

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mView;

    public T getView() {
        return mView;
    }

    public boolean isViewAvailable() {
        return mView != null;
    }

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
