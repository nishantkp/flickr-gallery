/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nishant.flickr101.R;
import com.android.nishant.flickr101.databinding.PhotoGridItemBinding;
import com.android.nishant.flickr101.ui.dashboard.DashboardActivity;
import com.android.nishant.flickr101.ui.model.PhotoDetail;
import com.squareup.picasso.Picasso;

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
        if (newData == null || newData.isEmpty()) return;
        mPhotoDetailList.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_grid_item, parent, false);
        return new PhotoViewHolder(PhotoGridItemBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.bind(mPhotoDetailList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotoDetailList == null ? 0 : mPhotoDetailList.size();
    }

    /**
     * ViewHolder for RecyclerView Adapter
     */
    class PhotoViewHolder extends RecyclerView.ViewHolder {

        private PhotoGridItemBinding mBinding;

        PhotoViewHolder(PhotoGridItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(PhotoDetail photoDetail) {
            mBinding.photoGridTitle.setText(photoDetail.getTitle());
            Picasso.get().load(photoDetail.getUrl()).into(mBinding.photoGridImage);
        }
    }
}
