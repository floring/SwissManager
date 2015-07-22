package com.arles.swissmanager.ui.presenter;

import android.util.Log;
import android.widget.Toast;

import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.algorithm.Match;
import com.arles.swissmanager.algorithm.Points;
import com.arles.swissmanager.algorithm.Report;
import com.arles.swissmanager.algorithm.Tournament;
import com.arles.swissmanager.utils.ToastUtil;

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

    public void onMatchClicked(Match match, Points resPlayer1, Points resPlayer2) {
        Report report = match.reportResult(resPlayer1, resPlayer2);
        if (report == Report.OK) {
            mView.showOkMessage();
            mView.setBtn();
        } else if (report == Report.INVALID_RESULT) {
            mView.showIncorrectResultMessage();
        }
    }

    public interface IView {
        void setViewComponent();
        void showIncorrectResultMessage();
        void showOkMessage();
        void setBtn();
    }
}
