package com.arles.swissmanager.ui.presenter;

import android.content.Intent;
import android.view.textservice.TextInfo;

import com.arles.swissmanager.algorithm.Tournament;
import com.arles.swissmanager.ui.model.Player;
import com.arles.swissmanager.utils.KeyExtra;
import com.arles.swissmanager.utils.TextUtil;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * PlayerDataPresenter created to show PlayerDataActivity.
 * Created by Admin on 30.07.2015.
 */
public class PlayerDataPresenter extends Presenter {

    private IView mView;
    private Tournament mTournament;
    private Player mPlayer;

    @Inject
    public PlayerDataPresenter() {
        mTournament = Tournament.getInstance();
    }

    public void setView(IView view) {
        mView = view;
    }

    @Override
    public void initializeViewComponent() {
        mView.setViewComponent();
    }

    public void getPassedExtrasFrom(Intent intent) {
        if(intent != null) {
            int listNum = intent.getIntExtra(KeyExtra.KEY_PLAYER_LIST_POSITION, KeyExtra.DEFAULT_LIST_POSITION_TO_DISPLAY);
            mPlayer = mTournament.getPlayerCollection().get(listNum);
        }
    }

    public CharSequence getPlayerName() {
        return (mPlayer!= null) ? mPlayer.getName() : TextUtil.EMPTY_STRING;
    }

    public CharSequence getPlayerPrestige() {
        return (mPlayer!= null) ? Integer.toString(mPlayer.getPrestige()) : TextUtil.EMPTY_STRING;
    }

    public Set<Player> getPlayerRivalsCollection() {
        return (mPlayer != null) ? mPlayer.getRivals() : null;
    }

    public interface IView {
        void setViewComponent();
    }
}
