package com.arles.swissmanager.algorithm;

import com.arles.swissmanager.ui.model.Player;

/**
 * Match class represents Match entity in game.
 * Created by Admin on 27.03.2015.
 */
public class Match {
    private Player mPlayer1;
    private Player mPlayer2;
    private Result mResult;

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

    public Result getResult() {
        return mResult;
    }

    /**
     * Define winner, calculate reportResult
     */
    public void reportResult(int player1score, int player2score) {

        mPlayer1.setGamesFor(player1score);
        mPlayer1.setGamesAgainst(player2score);
        mPlayer2.setGamesFor(player2score);
        mPlayer2.setGamesAgainst(player1score);

        int currentScore = player1score - player2score;
        if (currentScore > 0) {
            morePoints(currentScore);
        } else if (currentScore < 0) {
            lessPoints(currentScore);
        } else {
            tiePoints(player1score);
        }
    }

    /*
    Make result when 1st player has more points than 2nd player.
    */
    private void morePoints(int score) {
        if (score > 1) {
            mPlayer1.won();
            mResult = Result.WIN_LOSE;
        } else {
            mPlayer1.draw();
            mResult = Result.DRAW_LOSE;
        }
        mPlayer2.lost();
    }

    /*
    Make result when 1st player has less points than 2nd player.
    */
    private void lessPoints(int score) {
        if (score < -1) {
            mPlayer2.won();
            mResult = Result.LOSE_WIN;
        } else {
            mPlayer2.draw();
            mResult = Result.LOSE_DRAW;
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
            mResult = Result.DRAW_DRAW;
        } else {
            mPlayer1.lost();
            mPlayer2.lost();
            mResult = Result.LOSE_LOSE;
        }
    }
}
