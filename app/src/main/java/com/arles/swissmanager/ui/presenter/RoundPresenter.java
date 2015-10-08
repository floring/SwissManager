/**
 * Copyright (C) 2015 Arles
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
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import com.arles.swissmanager.R;
import com.arles.swissmanager.tournament.Match;
import com.arles.swissmanager.tournament.Player;
import com.arles.swissmanager.tournament.Points;
import com.arles.swissmanager.tournament.Report;
import com.arles.swissmanager.tournament.Round;
import com.arles.swissmanager.tournament.Tournament;
import com.arles.swissmanager.utils.KeyExtra;

import java.util.List;

import javax.inject.Inject;

/**
 * RoundPresenter created to show RoundActivity.
 * Created by Admin on 16.07.2015.
 */
public class RoundPresenter extends Presenter {

    private IView mView;
    private Tournament mTournament;
    private Round mRound;
    private Context mContext;

    @Inject
    public RoundPresenter(Context context) {
        mTournament = Tournament.getInstance();
        mContext = context;
    }

    public void setView(IView view) {
        mView = view;
    }

    @Override
    public void initializeViewComponent() {
        mView.setViewComponent();
        isByePlayerExists();
    }

    public int getRoundNumber() {
        return (mRound != null) ? mRound.getNumber() : 0;
    }

    public List<Match> getMatchesData() {
        return (mRound != null) ? mRound.getMatches() : null;
    }

    public void getPassedExtrasFrom(Intent intent) {
        if (intent != null) {
            int listNum = intent.getIntExtra(KeyExtra.KEY_ROUND_LIST_POSITION, KeyExtra.DEFAULT_LIST_POSITION_TO_DISPLAY);
            mRound = mTournament.getRoundCollection().get(listNum);
        }
    }

    public void onMatchClicked(View v, Match match, Points resPlayer1, Points resPlayer2) {
        Report report = match.reportResult(resPlayer1, resPlayer2);
        if (report == Report.OK) {
            mView.showReportResultMessage(mContext.getString(R.string.result_ok));
            mView.setEnabled(v, false);
        } else if (report == Report.INVALID_RESULT) {
            mView.showReportResultMessage(mContext.getString(R.string.result_invalid));
        }
    }

    public void startRoundAction(MenuItem item) {
        mRound.startRound();
        mView.showRoundMessage(mContext.getString(R.string.result_round_started));
    }

    public void endRoundAction(MenuItem item) {
        mRound.endRound();
        mView.showRoundMessage(mContext.getString(R.string.result_round_ended));
    }

    public void setActionRoundView(MenuItem startActionItem, MenuItem endActionItem) {
        final int opacityWhenEnabled = 255;
        final int opacityWhenDisabled = 64;
        switch (mRound.getState()) {
            case CREATED:
                startActionItem.setEnabled(true);
                endActionItem.setEnabled(true);
                mView.setMenuItemOpacity(startActionItem, opacityWhenEnabled);
                mView.setMenuItemOpacity(endActionItem, opacityWhenEnabled);
                break;
            case RUNNING:
                startActionItem.setEnabled(false);
                endActionItem.setEnabled(true);
                mView.setMenuItemOpacity(startActionItem, opacityWhenDisabled);
                mView.setMenuItemOpacity(endActionItem, opacityWhenEnabled);
                break;
            case COMPLETED:
                startActionItem.setEnabled(false);
                endActionItem.setEnabled(false);
                mView.setMenuItemOpacity(startActionItem, opacityWhenDisabled);
                mView.setMenuItemOpacity(endActionItem, opacityWhenDisabled);
                break;
            default:
                break;
        }
    }

    private void isByePlayerExists() {
        Player byePlayer = mRound.getByePlayer();
        if(byePlayer != null) {
            mView.inflateByePlayerView(byePlayer.getName());
        }
    }

    public interface IView {
        void setViewComponent();

        void showReportResultMessage(String msg);

        void showRoundMessage(String msg);

        void setEnabled(View view, boolean enabled);

        void setMenuItemOpacity(MenuItem item, int alpha);

        void inflateByePlayerView(String name);
    }
}
