package com.arles.swissmanager.di;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;

import com.arles.swissmanager.SwissManagerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module created to work as junction of every module with an application scope.
 * <p/>
 * This module provides every application scope dependencies related with the AndroidSDK.
 * <p/>
 * Created by Admin on 10.04.2015.
 */

@Module(
        injects = {
                SwissManagerApplication.class
        }, library = true)
public final class RootModule {

    private final Context context;

    public RootModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(context);
    }
}
