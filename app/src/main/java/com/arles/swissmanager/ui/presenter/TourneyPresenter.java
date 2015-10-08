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

package com.arles.swissmanager.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.arles.swissmanager.R;
import com.arles.swissmanager.tournament.Round;
import com.arles.swissmanager.tournament.Tournament;
import com.arles.swissmanager.ui.activity.Navigator;

import javax.inject.Inject;

/**
 * TourneyPresenter created to show TourneyActivity.
 * Created by Admin on 02.07.2015.
 */
public class TourneyPresenter extends Presenter {

    private IView mView;
    private Tournament mTournament;
    private Context mContext;
    private Navigator mNavigator;

    @Inject
    public TourneyPresenter(Context context, Navigator navigator) {
        mTournament = Tournament.getInstance();
        mContext = context;
        mNavigator = navigator;
    }

    public void setView(IView view) {
        mView = view;
    }

    @Override
    public void initializeViewComponent() {
        mView.setViewComponent();
    }

    public void createNewRoundAction() {
        Round round = createRound();
        if (round != null) {
            mView.showRoundMessage(mContext.getString(R.string.result_round_added));
            mView.refreshFragmentData(mView.getPagerAdapterRoundTabPosition());
        } else {
            mView.showDialog(mContext.getString(R.string.result_cant_start_new_round));
        }
    }

    private Round createRound() {
        return mTournament.createNewRound();
    }

    public void getTotalRoundNumber() {
        int number = mTournament.calculateRoundsNumber(mTournament.getPlayersCount());
        mView.showDialog(mContext.getString(R.string.result_total_rounds_number).concat(Integer.toString(number)));
    }

    public void sortByPrestige() {
        mTournament.sortPlayersByPrestige();
        mView.refreshFragmentData(mView.getPagerAdapterPlayerTabPosition());
    }

    public void openPreferences() {
        mNavigator.startPreferencesActivity();
    }

    public void setTourneySettingsFromPrefs() {
        PreferenceManager.setDefaultValues(mContext, R.xml.preferences, false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        int win = prefs.getInt(mContext.getString(R.string.pref_key_point_win), 0);
        int lose = prefs.getInt(mContext.getString(R.string.pref_key_point_lose), 0);
        int draw = prefs.getInt(mContext.getString(R.string.pref_key_point_draw), 0);
        int bye = prefs.getInt(mContext.getString(R.string.pref_key_point_bye), 0);
        mTournament.setPointsValue(win, lose, draw, bye);

    }

    public void clearPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        prefs.edit().clear().apply();
    }

    public interface IView {
        void setViewComponent();

        void showRoundMessage(String msg);

        void showDialog(CharSequence content);

        void refreshFragmentData(int position);

        int getPagerAdapterPlayerTabPosition();

        int getPagerAdapterRoundTabPosition();
    }
}
