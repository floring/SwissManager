package com.arles.swissmanager.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.arles.swissmanager.R;
import com.arles.swissmanager.algorithm.Match;
import com.arles.swissmanager.algorithm.Points;
import com.arles.swissmanager.algorithm.Report;
import com.arles.swissmanager.algorithm.Round;
import com.arles.swissmanager.algorithm.State;
import com.arles.swissmanager.algorithm.Tournament;
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

    public void startRoundAction() {
        toggleRoundState(null, State.RUNNING);
        mView.showRoundMessage(mContext.getString(R.string.result_round_started));
    }

    public void endRoundAction() {
        mTournament.endRound(mRound);
        toggleRoundState(State.RUNNING, State.COMPLETED);
        mView.showRoundMessage(mContext.getString(R.string.result_round_ended));
    }

    private void toggleRoundState(State currentState, State newState) {
        if (mRound.state == currentState) {
            mRound.state = newState;
        }
    }

    public interface IView {
        void setViewComponent();

        void showReportResultMessage(String msg);

        void showRoundMessage(String msg);

        void setEnabled(View view, boolean enabled);
    }
}
