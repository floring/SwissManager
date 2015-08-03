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
            mView.showDialog(mContext.getString(R.string.result_cant_start_new_round));
        }
    }

    private Round createRound() {
        return mTournament.startRound();
    }

    public void getTotalRoundNumber() {
        int number = mTournament.calculateRoundsNumber(mTournament.getPlayersCount());
        mView.showDialog(mContext.getString(R.string.result_total_rounds_number).concat(Integer.toString(number)));
    }

    public void sortByPrestige() {
        mTournament.sortPlayersByPrestige();
        mView.refreshFragmentData(mView.getPagerAdapterPlayerTabPosition());
    }

    public interface IView {
        void setViewComponent();

        void showRoundMessage(String msg);

        void showDialog(CharSequence content);

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
