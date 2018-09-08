/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * POJO associated with JSON response for querying size of images
 */
public class Sizes {
    @SerializedName("canblog")
    @Expose
    private Integer canBlog;
    @SerializedName("canprint")
    @Expose
    private Integer canPrint;
    @SerializedName("candownload")
    @Expose
    private Integer canDownload;
    @SerializedName("size")
    @Expose
    private List<Size> size = null;

    public Integer getCanblog() {
        return canBlog;
    }

    public void setCanBlog(Integer canBlog) {
        this.canBlog = canBlog;
    }

    public Integer getCanPrint() {
        return canPrint;
    }

    public void setCanPrint(Integer canPrint) {
        this.canPrint = canPrint;
    }

    public Integer getCanDownload() {
        return canDownload;
    }

    public void setCanDownload(Integer canDownload) {
        this.canDownload = canDownload;
    }

    public List<Size> getSize() {
        return size;
    }

    public void setSize(List<Size> size) {
        this.size = size;
    }
}
