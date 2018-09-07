/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.nishant.flickr101.R;

public class DashboardActivity
        extends AppCompatActivity
        implements DashboardContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
}
