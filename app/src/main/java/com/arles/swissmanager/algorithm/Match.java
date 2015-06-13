package com.arles.swissmanager.algorithm;

import com.arles.swissmanager.ui.model.Player;

/**
 * Created by Admin on 27.03.2015.
 */
public class Match {
    private Player mPlayer1;
    private Player mPlayer2;

    public Match(Player p1, Player p2) {
        mPlayer1 = p1;
        mPlayer2 = p2;
        mPlayer1.playWith(mPlayer2);
        mPlayer1.playWith(mPlayer1);
    }

    public Player getPlayer1() {
        return mPlayer1;
    }

    public Player getPlayer2() {
        return mPlayer2;
    }

    /**
     * Define winner, calculate score
     */
    public Player score(int player1score, int player2score) {
        Player winner = null;

        mPlayer1.setGamesFor(player1score);
        mPlayer2.setGamesAgainst(player2score);
        mPlayer2.setGamesFor(player2score);
        mPlayer2.setGamesAgainst(player1score);

        int currentScore = player1score - player2score;
        if(currentScore > 0) {
            mPlayer1.won();
            mPlayer2.loss();
            winner = mPlayer1;
        } else if(currentScore < 0) {
            mPlayer2.won();
            mPlayer1.loss();
            winner = mPlayer2;
        } else {
            mPlayer1.draw();
            mPlayer2.draw();
        }
        return winner;
    }
}
