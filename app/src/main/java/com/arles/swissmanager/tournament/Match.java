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

/**
 * Match class represents Match entity in game.
 * Created by Admin on 27.03.2015.
 */
public class Match {
    private Player mPlayer1;
    private Player mPlayer2;
    private MatchResult mResult;

    public Match(Player p1, Player p2) {
        mPlayer1 = p1;
        mPlayer2 = p2;
        mResult = null;
        mPlayer1.playWith(mPlayer2);
        mPlayer2.playWith(mPlayer1);
    }

    public Player getPlayer1() {
        return mPlayer1;
    }

    public Player getPlayer2() {
        return mPlayer2;
    }

    public MatchResult getResult() {
        return mResult;
    }

    /**
     * Set information to players of played match.
     * Return operation report.
     */
    public Report reportResult(Points player1score, Points player2score) {

        if(!MatchResult.isResultCorrect(player1score, player2score)) {
            return Report.INVALID_RESULT;
        }

        mPlayer1.setGamesFor(player1score.get());
        mPlayer1.setGamesAgainst(player2score.get());
        mPlayer2.setGamesFor(player2score.get());
        mPlayer2.setGamesAgainst(player1score.get());

        int currentScore = player1score.get() - player2score.get();
        if (currentScore > 0) {
            morePoints(currentScore);
        } else if (currentScore < 0) {
            lessPoints(currentScore);
        } else {
            tiePoints(player1score.get());
        }
        return Report.OK;
    }

    /*
    Make result when 1st player has more points than 2nd player.
    */
    private void morePoints(int score) {
        if (score > 1) {
            mPlayer1.won();
            mResult = new MatchResult(Points.Win, Points.Lose);
        } else {
            mPlayer1.draw();
            mResult = new MatchResult(Points.Draw, Points.Lose);
        }
        mPlayer2.lost();
    }

    /*
    Make result when 1st player has less points than 2nd player.
    */
    private void lessPoints(int score) {
        if (score < -1) {
            mPlayer2.won();
            mResult = new MatchResult(Points.Lose, Points.Win);
        } else {
            mPlayer2.draw();
            mResult = new MatchResult(Points.Lose, Points.Draw);
        }
        mPlayer1.lost();
    }

    /*
    Make result when players are tied.
    */
    private void tiePoints(int score) {
        if (score > 0) {
            mPlayer1.draw();
            mPlayer2.draw();
            mResult = new MatchResult(Points.Draw, Points.Draw);
        } else {
            mPlayer1.lost();
            mPlayer2.lost();
            mResult = new MatchResult(Points.Lose, Points.Lose);
        }
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(mPlayer1.getName())
                .append('-')
                .append(mPlayer2.getName())
                .toString();
    }
}

