/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.android.nishant.flickr101.BR;
import com.android.nishant.flickr101.ui.dashboard.DashboardActivity;

/**
 * POJO for two way data binding to {@link DashboardActivity} search edit text
 */
public class UserSearchQuery
        implements Observable {

    private String mSearchQuery;
    private PropertyChangeRegistry mRegistry = new PropertyChangeRegistry();

    @Bindable
    public String getSearchQuery() {
        return mSearchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.mSearchQuery = searchQuery;
        mRegistry.notifyChange(this, BR.searchQuery);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        mRegistry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        mRegistry.remove(callback);
    }
}