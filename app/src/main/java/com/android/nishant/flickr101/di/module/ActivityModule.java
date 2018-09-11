/*
 * Copyright (c) 2018
 * File Created by nishant
 */

package com.android.nishant.flickr101.di.module;

import android.app.Activity;

import com.android.nishant.flickr101.di.scope.ActivityScope;
import com.android.nishant.flickr101.ui.dashboard.DashboardActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Activity module which creates dependency map
 */
@Module(subcomponents = ActivityModule.DashboardActivitySubComponent.class)
public abstract class ActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(DashboardActivity.class)
    public abstract AndroidInjector.Factory<? extends Activity>
    bindDashboardInjector(DashboardActivitySubComponent.Builder builder);

    @ActivityScope
    @Subcomponent(modules = DashboardActivityModule.class)
    public interface DashboardActivitySubComponent extends AndroidInjector<DashboardActivity> {
        @Subcomponent.Builder
        abstract class Builder extends AndroidInjector.Builder<DashboardActivity> {
        }
    }
}
