package com.arles.swissmanager.ui.presenter;

import android.content.Context;
import android.view.MenuItem;

import com.arles.swissmanager.R;
import com.arles.swissmanager.algorithm.Round;
import com.arles.swissmanager.algorithm.Sorter;
import com.arles.swissmanager.algorithm.Tournament;
import com.arles.swissmanager.ui.model.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * TourneyPresenter created to show TourneyActivity.
 * Created by Admin on 02.07.2015.
 */
public class TourneyPresenter extends Presenter {

    private IView mView;
    private Tournament mTournament;
    private Context mContext;

    @Inject
    public TourneyPresenter(Context context) {
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

    public void createNewRoundAction() {
        Round round = createRound();
        if (round != null) {
            mView.showRoundMessage(mContext.getString(R.string.result_round_added));
            mView.refreshFragmentData(mView.getPagerAdapterRoundTabPosition());
        } else {
            mView.showDialog("Can't start new round. Some rounds may running or rounds number is over");
        }
    }

    private Round createRound() {
        return mTournament.startRound();
    }

//    public void startRoundAction(MenuItem item) {
//        if(item.isEnabled()) {
//            Round round = createRound();
//            if (round != null) {
//                mView.showRoundMessage(mContext.getString(R.string.result_round_added));
//                //mView.setMenuItemEnabled(item, false);
//            }
//        } else {
//            mView.showWarningRoundMessage(mContext.getString(R.string.result_warning_round_started));
//        }
//    }
//
//    public void endRoundAction(MenuItem item) {
//        if(item.isEnabled()) {
//            endRound();
//            mView.showRoundMessage(mContext.getString(R.string.result_round_ended));
//            //mView.setMenuItemEnabled(item, false);
//        }
//        else {
//            mView.showWarningRoundMessage(mContext.getString(R.string.result_warning_round_ended));
//        }
//    }
//
//    private void endRound() {
//        //mTournament.endRound();
//    }

    public void getTotalRoundNumber() {
        int number = mTournament.calculateRoundsNumber();
        mView.showDialog("The total number of round is " + number);
    }

    public void getTourneyWinner() {
        Player player = mTournament.defineWinner();
        if (player != null) {
            mView.showDialog("The winner is " + player.getName());
        } else {
            mView.showDialog("The winner has not been determined");
        }
    }

    public void sortByPrestige() {
        mTournament.sortPlayersByPrestige();
        mView.refreshFragmentData(mView.getPagerAdapterPlayerTabPosition());
    }

    public interface IView {
        void setViewComponent();

        void showRoundMessage(String msg);

        void showDialog(CharSequence content);

        void showWarningRoundMessage(String msg);

        void setMenuItemEnabled(MenuItem item, boolean enabled);

        void refreshFragmentData(int position);

        int getPagerAdapterPlayerTabPosition();

        int getPagerAdapterRoundTabPosition();

    }

    /*
    private void setMenuItemEnable(MenuItem item, boolean enabled) {
    int curstate = ((Integer)item.getTag()).intValue();

    if(curState == 1 && enabled || curstate == 0 && !enabled) {
        return;
    }

    if(enabled) {
        ... //update image to remove dimming
        item.setTag(1);
    }
    else {
        ... //update image by dimming it
        item.setTag(0);
    }
}
     */
}
