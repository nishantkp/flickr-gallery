/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Activity level scope
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ActivityScope {
}
