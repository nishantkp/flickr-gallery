/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.data.callback;

import com.android.nishant.flickr101.ui.model.PhotoDetail;

import java.util.List;

/**
 * Task completion callback for data and error
 */
public interface OnTaskCompletion {
    void onData(List<PhotoDetail> data);

    void onError(String message);
}
