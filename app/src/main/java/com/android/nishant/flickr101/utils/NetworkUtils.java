/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.utils;

import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public final class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    /**
     * Call this method to generate photo URL from fields in Flicker endpoint
     * <p>
     * Generated url will be of form,
     * "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg"
     *
     * @param farmId   farm id from Flickr endpoint
     * @param serverId Server id from Flickr endpoint
     * @param id       id from Flickr endpoint
     * @param secret   secret key from Flickr endpoint
     * @return Url string for photo
     */
    public static String generatePhotoUrl(String farmId, String serverId, String id, String secret) {
        return "https://farm" + farmId + ".staticflickr.com/" + serverId +
                "/" + id + "_" + secret + ".jpg";
    }

    /**
     * The following endpoint will be the same
     * <p>
     * URL Type:
     * <p>
     * http://farm2.staticflickr.com/1103/{567229075}_2cf8456f01_s.jpg
     * From the curly braces in above link we can get photo id
     *
     * @param url photo url of
     * @return photo id extracted from url
     */
    public static String getImageIdFromFlickrUrl(String url) {
        String[] tempSplit = url.split("/");
        String stringWithId = tempSplit[4]; // 567229075_2cf8456f01_s.jpg
        String[] ids = stringWithId.split("_");
        return ids[0]; //567229075
    }

    /**
     * Call this method to get the size of file from url
     *
     * @param url Url of a file in which we are interested in
     * @return file size in string format like 1.2Mb, 12.23Kb and so on
     */
    public static String getByteSizeFromUrl(String url) {
        URL urlObject;
        URLConnection urlConnection = null;
        long size = 0;
        try {
            urlObject = new URL(url);
            urlConnection = urlObject.openConnection();
            size = urlConnection.getContentLength();
        } catch (IOException e) {
            Log.i(LOG_TAG, "Error opening url connection");
        } finally {
            if (urlConnection != null) {
                try {
                    urlConnection.getInputStream().close();
                } catch (IOException e) {
                    Log.i(LOG_TAG, "Error closing url connection");
                }
            }
        }
        return getFileSizeString(size);
    }

    /**
     * Call this method to get the size of file in string format
     *
     * @param size file size in long
     * @return file size in string format like 1.2Mb, 12.23Kb and so on
     */
    private static String getFileSizeString(long size) {

        DecimalFormat df = new DecimalFormat("0.00");

        float sizeKb = 1024.0f;
        float sizeMb = sizeKb * sizeKb;
        float sizeGb = sizeMb * sizeKb;
        float sizeTerra = sizeGb * sizeKb;

        if (size < sizeMb)
            return df.format(size / sizeKb) + " Kb";
        else if (size < sizeGb)
            return df.format(size / sizeMb) + " Mb";
        else if (size < sizeTerra)
            return df.format(size / sizeGb) + " Gb";

        return "";
    }
}
