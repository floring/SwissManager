package com.arles.swissmanager.ui.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.algorithm.Match;
import com.arles.swissmanager.algorithm.Points;
import com.arles.swissmanager.algorithm.Report;
import com.arles.swissmanager.algorithm.Round;
import com.arles.swissmanager.algorithm.Tournament;
import com.arles.swissmanager.utils.KeyExtra;
import com.arles.swissmanager.utils.ToastUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * RoundPresenter created to show RoundActivity.
 * Created by Admin on 16.07.2015.
 */
public class RoundPresenter extends Presenter {

    private IView mView;
    private Tournament mTournament;
    private final int DEFAULT_ROUND_NUMBER_TO_DISPLAY = 0;
    private Round mRound;

    @Inject
    public RoundPresenter() {
        mTournament = Tournament.getInstance();
    }

    public void setView(IView view) {
        mView = view;
    }

    @Override
    public void initializeViewComponent() {
        mView.setViewComponent();
    }

    public int getRoundNumber() {
        return (mRound != null) ? mRound.getNumber() : 0;
    }

    public List<Match> getMatchesData() {
        return (mRound != null) ? mRound.getMatches() : null;
    }

    public void getPassedExtrasFrom(Intent intent) {
        if(intent != null) {
            int listNum = intent.getIntExtra(KeyExtra.KEY_ROUND_LIST_POSITION, DEFAULT_ROUND_NUMBER_TO_DISPLAY);
            mRound = mTournament.getRoundCollection().get(listNum);
        }

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
