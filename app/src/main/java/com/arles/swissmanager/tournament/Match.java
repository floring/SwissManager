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
            mResult = new MatchResult(Points.WIN, Points.LOSE);
        } else {
            mPlayer1.draw();
            mResult = new MatchResult(Points.DRAW, Points.LOSE);
        }
        mPlayer2.lost();
    }

    /*
    Make result when 1st player has less points than 2nd player.
    */
    private void lessPoints(int score) {
        if (score < -1) {
            mPlayer2.won();
            mResult = new MatchResult(Points.LOSE, Points.WIN);
        } else {
            mPlayer2.draw();
            mResult = new MatchResult(Points.LOSE, Points.DRAW);
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
            mResult = new MatchResult(Points.DRAW, Points.DRAW);
        } else {
            mPlayer1.lost();
            mPlayer2.lost();
            mResult = new MatchResult(Points.LOSE, Points.LOSE);
        }
    }
}

