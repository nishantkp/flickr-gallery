/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.model;

/**
 * POJO for photo details like title, url
 */
public class PhotoDetail {

    private String mTitle;
    private String mUrl;
    private String mId;
    private String mWidth;
    private String mHeight;
    private String mOriginalUrl;
    private String mByteSize;

    public PhotoDetail(String title, String url, String id) {
        mTitle = title;
        mUrl = url;
        mId = id;
    }

    public PhotoDetail(String id, String width, String height, String originalUrl, String byteSize) {
        mId = id;
        mWidth = width;
        mHeight = height;
        mOriginalUrl = originalUrl;
        mByteSize = byteSize;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getWidth() {
        return mWidth;
    }

    public void setWidth(String width) {
        this.mWidth = width;
    }

    public String getHeight() {
        return mHeight;
    }

    public void setHeight(String height) {
        this.mHeight = height;
    }

    public String getOriginalUrl() {
        return mOriginalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.mOriginalUrl = originalUrl;
    }

    public String getByteSize() {
        return mByteSize;
    }

    public void setByteSize(String byteSize) {
        this.mByteSize = byteSize;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.equals(this.mId) || super.equals(obj);
    }
}
