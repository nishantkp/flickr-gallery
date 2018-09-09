/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.android.nishant.flickr101.config.IConstants;
import com.android.nishant.flickr101.ui.model.PhotoDetail;

import java.util.List;

public final class PhotoListDiffUtilsCallback extends DiffUtil.Callback {

    private List<PhotoDetail> mOldList;
    private List<PhotoDetail> mNewList;

    PhotoListDiffUtilsCallback(List<PhotoDetail> oldList, List<PhotoDetail> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList != null ? mOldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewList != null ? mNewList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getId()
                .equals(mNewList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        PhotoDetail oldItem = mOldList.get(oldItemPosition);
        PhotoDetail newItem = mNewList.get(newItemPosition);
        return oldItem.getWidthByHeight().equals(newItem.getWidthByHeight())
                && oldItem.getByteSize().equals(newItem.getByteSize());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        PhotoDetail oldItem = mOldList.get(oldItemPosition);
        PhotoDetail newItem = mNewList.get(newItemPosition);

        Bundle bundle = new Bundle();
        if (!oldItem.getWidthByHeight().equals(newItem.getWidthByHeight())) {
            bundle.putString(IConstants.KEY_WIDTH_HEIGHT, newItem.getWidthByHeight());
        }
        if (!oldItem.getByteSize().equals(newItem.getByteSize())) {
            bundle.putString(IConstants.KEY_BYTE_SIZE, newItem.getByteSize());
        }
        return bundle;
    }
}
