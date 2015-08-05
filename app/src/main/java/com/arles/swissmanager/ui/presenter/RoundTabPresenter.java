package com.arles.swissmanager.ui.presenter;

import com.arles.swissmanager.tournament.Round;
import com.arles.swissmanager.tournament.Tournament;
import com.arles.swissmanager.ui.activity.Navigator;

import java.util.List;

import javax.inject.Inject;

/**
 * RoundTabPresenter created to show RoundTabFragment.
 * Created by Admin on 24.07.2015.
 */
public class RoundTabPresenter extends Presenter {

    private IView mView;
    private Tournament mTournament;
    private Navigator mNavigator;

    @Inject
    public RoundTabPresenter(Navigator navigator) {
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

    public List<Round> getRoundList() {
        return mTournament.getRoundCollection();
    }

    public void onRoundItemClick(int position) {
        mNavigator.startRoundActivity(position);
    }

    public interface IView {
        void setViewComponent();
    }
}
