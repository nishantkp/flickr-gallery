/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nishant.flickr101.R;
import com.android.nishant.flickr101.config.IConstants;
import com.android.nishant.flickr101.databinding.PhotoListItemBinding;
import com.android.nishant.flickr101.ui.dashboard.DashboardActivity;
import com.android.nishant.flickr101.ui.model.PhotoDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView Adapter for displaying list of photos into {@link DashboardActivity}
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private List<PhotoDetail> mPhotoDetailList;

    public PhotoAdapter() {
        mPhotoDetailList = new ArrayList<>();
    }

    /**
     * Call this method to update the data set in RecyclerView list
     *
     * @param newData New batch of data
     */
    public void updateDataSet(List<PhotoDetail> newData) {
        DiffUtil.DiffResult diffResult =
                DiffUtil.calculateDiff(new PhotoListDiffUtilsCallback(mPhotoDetailList, newData));
        mPhotoDetailList.clear();
        mPhotoDetailList.addAll(newData);
        diffResult.dispatchUpdatesTo(this);
    }

    /**
     * Call this method to clear the data set
     */
    public void clearData() {
        mPhotoDetailList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_list_item, parent, false);
        return new PhotoViewHolder(PhotoListItemBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            if (bundle != null) {
                if (bundle.containsKey(IConstants.KEY_WIDTH_HEIGHT)) {
                    String widthLength = bundle.getString(IConstants.KEY_WIDTH_HEIGHT);
                    holder.mBinding.photoListItemImageWl.setText(widthLength);
                }
                if (bundle.containsKey(IConstants.KEY_BYTE_SIZE)) {
                    String byteSize = bundle.getString(IConstants.KEY_BYTE_SIZE);
                    holder.mBinding.photoListItemSize.setText(byteSize);
                }
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.bind(mPhotoDetailList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotoDetailList.size();
    }

    /**
     * ViewHolder for RecyclerView Adapter
     */
    class PhotoViewHolder extends RecyclerView.ViewHolder {

        private PhotoListItemBinding mBinding;

        PhotoViewHolder(PhotoListItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(PhotoDetail photoDetail) {
            mBinding.setPhoto(photoDetail);
//            mBinding.photoListItemTitle.setText(photoDetail.getTitle());
//            mBinding.photoListItemSize.setText(photoDetail.getByteSize());
//            mBinding.photoListItemImageWl.setText(photoDetail.getWidthByHeight());
//            Picasso.get().load(photoDetail.getUrl()).into(mBinding.photoListItemImage);
        }
    }
}
