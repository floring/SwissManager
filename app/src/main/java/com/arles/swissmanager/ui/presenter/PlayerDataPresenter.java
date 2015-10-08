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

import com.arles.swissmanager.tournament.Player;
import com.arles.swissmanager.tournament.Tournament;
import com.arles.swissmanager.utils.KeyExtra;
import com.arles.swissmanager.utils.TextUtil;

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
