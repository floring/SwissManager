/**
 * Copyright (C) 2015 Arles
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arles.swissmanager.ui.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.arles.swissmanager.tournament.Player;
import com.arles.swissmanager.tournament.Tournament;
import com.arles.swissmanager.ui.activity.Navigator;
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

    public List<Player> getPlayerList() {
        return mTournament.getPlayerCollection();
    }

    public void setPlayerList(List<Player> playerList) {
        mTournament.setPlayerCollection(playerList);
    }

    public void parseActivityResult(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            ArrayList<String> namesList = bundle.getStringArrayList(KeyExtra.KEY_BUFFER_PLAYERS_NAME_LIST);
            if (namesList != null) {
                for (String name : namesList) {
                    addPlayer(name);
                }
            }
        }
    }

    private void addPlayer(String playerName) {
        mTournament.addPlayer(playerName);
    }

    public void remove() {
        List<Integer> selectedItemPositions = mView.getSelectedInActionModeItemPositions();
        int currPos;
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            currPos = selectedItemPositions.get(i);
            // PlayerAdapter contains reference to players collection.
            // Therefore when player is removed from Tournament, it means it removed from adapter.
            mTournament.removePlayer(currPos);
        }
    }

    public void onPlayerItemClick(int position) {
        mNavigator.startPlayerDataActivity(position);
    }

    public interface IView {
        void setViewComponent();
        List<Integer> getSelectedInActionModeItemPositions();
    }
}
