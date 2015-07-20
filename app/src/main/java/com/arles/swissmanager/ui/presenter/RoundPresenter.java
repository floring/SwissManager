package com.arles.swissmanager.ui.presenter;

import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.algorithm.Match;
import com.arles.swissmanager.algorithm.Tournament;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Admin on 16.07.2015.
 */
public class RoundPresenter extends Presenter {

    private IView mView;

    @Inject
    public RoundPresenter() {
    }

    public void setView(IView view) {
        mView = view;
    }

    @Override
    public void initializeViewComponent() {
        mView.setViewComponent();
    }

    public int getRoundNumber() {
        //int roundNum = Tournament.getInstance().getRoundNumber();
        return 1;
    }

    public List<Match> getMatchesData() {
        // Tournament.getInstance().getMatches();
        return SwissManagerApplication.getTestMatchData();
    }

    public interface IView {
        void setViewComponent();
    }
}
