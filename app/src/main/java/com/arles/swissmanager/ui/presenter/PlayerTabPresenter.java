package com.arles.swissmanager.ui.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.arles.swissmanager.algorithm.Tournament;
import com.arles.swissmanager.ui.activity.Navigator;
import com.arles.swissmanager.ui.model.Player;
import com.arles.swissmanager.utils.KeyExtra;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * PlayerTabPresenter created to show PlayerTabFragment.
 * Created by Admin on 06.07.2015.
 */
public class PlayerTabPresenter extends Presenter {

    private IView mView;
    private Tournament mTournament;
    private Navigator mNavigator;

    @Inject
    public PlayerTabPresenter(Navigator navigator) {
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

    public void launchActivity(int requestCode) {

    }

    public List<Player> getPlayerList() {
        return mTournament.getPlayerCollection();
    }

    public void setPlayerList(List<Player> playerList) {
        mTournament.setPlayers(playerList);
    }

    public void parseActivityResult(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            ArrayList<String> namesList = bundle.getStringArrayList(KeyExtra.KEY_BUFFER_PLAYERS_NAME_LIST);
            if(namesList != null) {
                mView.addToAdapter(namesList);
            }
        }
    }

    public void onPlayerItemClick(int position) {
        mNavigator.startPlayerDataActivity(position);
    }

    public interface IView {
        void setViewComponent();
        void addToAdapter(ArrayList<String> namesList);
    }
}
