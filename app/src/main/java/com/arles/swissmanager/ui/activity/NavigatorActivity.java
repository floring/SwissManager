package com.arles.swissmanager.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.arles.swissmanager.di.ActivityContext;
import com.arles.swissmanager.utils.KeyExtra;

import javax.inject.Inject;

/**
 * Class created to handle all the navigation between activities. This class knows how to open
 * every activity in the application and provides to the client code different methods to start
 * activities with the information needed.
 *
 * Created by Admin on 19.05.2015.
 */
public class NavigatorActivity {

    private Activity mActivityContext;

    @Inject
     public NavigatorActivity(@ActivityContext Context context) {
        mActivityContext = (Activity) context;
    }

    public void startRoundActivity(int roundListPosition) {
        Intent intent = getLaunchIntent(RoundActivity.class);
        intent.putExtra(KeyExtra.KEY_ROUND_LIST_POSITION, roundListPosition);
        mActivityContext.startActivity(intent);
    }

    public void startNewPlayerActivityForResult(int requestCode) {
        Intent intent = getLaunchIntent(NewPlayerActivity.class);
        mActivityContext.startActivityForResult(intent, requestCode);
    }

    /**
     * Generates the intent needed by the client code to launch this activity.
     */
    private Intent getLaunchIntent(Class activityClass) {
        Intent intent = new Intent(mActivityContext, activityClass);
        return intent;
    }

}
