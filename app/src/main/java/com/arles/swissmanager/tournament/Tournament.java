/*
 * Copyright (C) 2015 Arles. All rights reserved.
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

package com.arles.swissmanager.tournament;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Tournament class is an entry point to game with swiss algorithm.
 * It handles basic operation on tourney e.g. create/remove player, start/end round.
 * This class uses singleton design pattern.
 * Created by Admin on 25.06.2015.
 */
public class Tournament {

    private static Tournament tournament;

    private List<Player> mPlayers = new ArrayList<>();
    private List<Round> mRounds = new ArrayList<>();
    private int mRoundNumber = 0;

    private Tournament() {
    }

    public static Tournament getInstance() {
        if (tournament == null) {
            tournament = new Tournament();
        }
        return tournament;
    }

    public void setPlayerCollection(List<Player> players) {
        if(players != null) {
            mPlayers = players;
        }
    }

    public void removePlayer(int position) {
        mPlayers.remove(position);
    }

    public void addPlayer(String name) {
        mPlayers.add(new Player(name));
    }

    public int calculateRoundsNumber(int playersSize) {
        return (playersSize > 1) ? (int) (Math.ceil(Math.log(playersSize)) +
                Math.ceil(Math.log(Math.min(2.0, playersSize - 1)))) : 0;
    }

    public Round createNewRound() {
        Round round = null;
        if (isGameCorrect()) {
            nextRound();
            MatchesCreator creator = new MatchesCreator();
            List<Match> matches = creator.createMatchList(new ArrayList<>(mPlayers));
            Player byePlayer = creator.getByePlayer();
            round = createRoundInstance(mRoundNumber, matches, byePlayer);
        }
        return round;
    }

    private boolean isGameCorrect() {
        return  (isAllRoundsCompleted() && isRoundsNumberNotOver());
    }

    private boolean isRoundsNumberNotOver() {
        return (mRoundNumber < calculateRoundsNumber(getPlayersCount()));
    }

    private boolean isAllRoundsCompleted() {
        for (Round round : mRounds) {
            if(round.getState() != State.COMPLETED) {
                return false;
            }
        }
        return true;
    }

    private void nextRound() {
        mRoundNumber++;
    }

    private Round createRoundInstance(int roundNumber, List<Match> matches, Player byePlayer) {
        Round round = new Round(roundNumber, matches, byePlayer);
        mRounds.add(round);
        return round;
    }

    public Collection sortPlayersByPrestige() {
        Collections.sort(mPlayers);
        return mPlayers;
    }

    public List<Round> getRoundCollection() {
        return mRounds;
    }

    public List<Player> getPlayerCollection() {
        return mPlayers;
    }

    public int getPlayersCount() {
        return (mPlayers != null) ? mPlayers.size() : 0;
    }

    public void setPointsValue(int winPoint, int losePoint, int drawPoint, int byePoint) {
        Points.Win.set(winPoint);
        Points.Lose.set(losePoint);
        Points.Draw.set((drawPoint));
        Points.Bye.set(byePoint);
    }
}
