package com.arles.swissmanager.ui.presenter;

import com.arles.swissmanager.algorithm.Round;
import com.arles.swissmanager.algorithm.Tournament;
import com.arles.swissmanager.ui.activity.NavigatorActivity;

import javax.inject.Inject;

/**
 * Created by Admin on 02.07.2015.
 */
public class TourneyPresenter extends Presenter {

    private IView mView;
    private NavigatorActivity mNavigator;
    private Tournament mTournament;

    @Inject
    public TourneyPresenter(NavigatorActivity navigator) {
        mNavigator = navigator;
        mTournament = Tournament.getInstance();
    }

    public void setView(IView view) {
        mView = view;
    }

    @Override
    public void initializeViewComponent() {
        mView.setViewComponent();
    }

    public void startRound() {
        // make start round action not visible
        // start round in tourney
        // start new activity
        Round r = mTournament.startRound();
        mNavigator.startRoundActivity();
    }

    public interface IView {
        void setViewComponent();
    }
}
