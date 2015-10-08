/*
 * Copyright (C) 2015 Arles. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
public class Navigator {

    private Activity mActivityContext;

    @Inject
     public Navigator(@ActivityContext Context context) {
        mActivityContext = (Activity) context;
    }

    public void startRoundActivity(int roundListPosition) {
        Intent intent = getLaunchIntent(RoundActivity.class);
        intent.putExtra(KeyExtra.KEY_ROUND_LIST_POSITION, roundListPosition);
        mActivityContext.startActivity(intent);
    }

    public void startPlayerDataActivity(int playerListPosition) {
        Intent intent = getLaunchIntent(PlayerDataActivity.class);
        intent.putExtra(KeyExtra.KEY_PLAYER_LIST_POSITION, playerListPosition);
        mActivityContext.startActivity(intent);
    }

    public void startPreferencesActivity() {
        Intent intent = getLaunchIntent(AppPreferencesActivity.class);
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
        return new Intent(mActivityContext, activityClass);
    }

}
