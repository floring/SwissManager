package com.arles.swissmanager.ui.presenter;

import com.arles.swissmanager.algorithm.Round;
import com.arles.swissmanager.algorithm.Tournament;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * RoundTabPresenter created to show RoundTabFragment.
 * Created by Admin on 24.07.2015.
 */
public class RoundTabPresenter extends Presenter {

    private IView mView;
    private Tournament mTournament;

    @Inject

    public RoundTabPresenter() {
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

    public interface IView {
        void setViewComponent();
    }
}
