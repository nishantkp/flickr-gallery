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

    private String searchQuery;
    private PropertyChangeRegistry registry = new PropertyChangeRegistry();

    @Bindable
    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
        registry.notifyChange(this, BR.searchQuery);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.remove(callback);
    }
}