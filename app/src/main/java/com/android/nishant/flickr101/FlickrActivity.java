/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.nishant.flickr101.ui.dashboard.DashboardActivity;

public class FlickrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr);

        startActivity(new Intent(this, DashboardActivity.class));
    }
}
