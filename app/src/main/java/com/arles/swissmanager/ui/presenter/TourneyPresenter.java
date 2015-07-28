package com.arles.swissmanager.ui.presenter;

import android.view.MenuItem;

import com.arles.swissmanager.algorithm.Round;
import com.arles.swissmanager.algorithm.Tournament;
import com.arles.swissmanager.ui.activity.NavigatorActivity;

import javax.inject.Inject;

/**
 * TourneyPresenter created to show TourneyActivity.
 * Created by Admin on 02.07.2015.
 */
public class TourneyPresenter extends Presenter {

    private IView mView;
    private Tournament mTournament;

    @Inject
    public TourneyPresenter() {
        mTournament = Tournament.getInstance();
    }

    public void setView(IView view) {
        mView = view;
    }

    @Override
    public void initializeViewComponent() {
        mView.setViewComponent();
    }

    public void startRound(MenuItem item) {
        // make start round action not visible
        // start round in tourney
        // start new activity
        Round round = mTournament.startRound();
        if(round != null) {
            mView.showRoundAddedMessage();
            mView.setMenuItemEnabled(item, false);
        }
    }

    public void endRound() {

    }

    public interface IView {
        void setViewComponent();
        void showRoundAddedMessage();
        void setMenuItemEnabled(MenuItem item, boolean enabled);
    }
}
