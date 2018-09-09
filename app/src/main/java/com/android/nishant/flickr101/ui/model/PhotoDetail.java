/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.model;

import java.util.Objects;

/**
 * POJO for photo details like title, url
 */
public class PhotoDetail {

    private String mTitle;
    private String mUrl;
    private String mId;
    private String mWidth = "NA";
    private String mHeight = "NA";
    private String mOriginalUrl;
    private String mByteSize = "NA";
    private String mWidthByHeight = "NA";

    public PhotoDetail(String title, String url, String id) {
        mTitle = title;
        mUrl = url;
        mId = id;
    }

    public PhotoDetail(String id, String width, String height, String widthByHeight, String originalUrl, String byteSize) {
        mId = id;
        mWidth = width;
        mHeight = height;
        mWidthByHeight = widthByHeight;
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
        if (obj instanceof PhotoDetail)
            return Objects.equals(((PhotoDetail) obj).getId(), this.mId);
        return super.equals(obj);
    }

    public String getWidthByHeight() {
        return mWidthByHeight;
    }

    public void setWidthByHeight(String widthByHeight) {
        this.mWidthByHeight = widthByHeight;
    }
}
