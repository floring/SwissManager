package com.arles.swissmanager.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.arles.swissmanager.di.ActivityContext;

import javax.inject.Inject;

/**
 * Class created to handle all the navigation between activities. This class knows how to open
 * every activity in the application and provides to the client code different methods to start
 * activities with the information needed.
 *
 * Created by Admin on 19.05.2015.
 */
public class Navigator {

    private final Context mContext;

    @Inject
    public Navigator(@ActivityContext Context context) {
        mContext = context;
    }

    public void openNewPlayerActivity() {
        Intent intent = NewPlayerActivity.getLaunchIntent(mContext);
        mContext.startActivity(intent);
    }
}
