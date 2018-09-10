/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.nishant.flickr101.R;
import com.android.nishant.flickr101.ui.dashboard.DashboardActivity;

/**
 * Splash screen activity
 */
public class FlickrActivity extends AppCompatActivity {

    // Time in milli seconds for splash screen
    private static final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr);

        // Wait for 2 seconds for splash screen, then start DashboardActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                startActivity(
                        DashboardActivity.getStarterIntent(FlickrActivity.this)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
                );
            }
        }, SPLASH_TIME_OUT);
    }
}
