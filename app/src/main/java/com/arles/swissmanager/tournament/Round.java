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
import java.util.List;

/**
 * Represents Round entity in game.
 * Created by Admin on 30.06.2015.
 */
public class Round {

    private int mNumber;
    private List<Match> mMatches = new ArrayList<>();
    private State mState;
    private Player mByePlayer;

    public Round(int number, List<Match> list, Player byePlayer) {
        mNumber = number;
        if (list != null) {
            mMatches = list;
        }
        mByePlayer = byePlayer;
        mState = State.CREATED;
    }

    public List<Match> getMatches() {
        return mMatches;
    }

    public int getNumber() {
        return mNumber;
    }

    public State getState() {
        return mState;
    }

    public Player getByePlayer() {
        return mByePlayer;
    }

    public void startRound() {
        // We can start the round only if the round had just created
        // Unable to start the already ended round
        if(mState == State.CREATED) {
            mState = State.RUNNING;
        }
    }

    public void endRound() {
        // We can't end up already completed round
        // We can end the round only if its state is not completed.
        if(mState != State.COMPLETED) {
            setUnplayedMatchAsLost(mMatches);
            mState = State.COMPLETED;
        }
    }

    private void setUnplayedMatchAsLost(List<Match> matches) {
        for (Match match : matches) {
            if (match.getResult() == null) {
                match.reportResult(Points.Lose, Points.Lose);
            }
        }
    }
}
