package com.arles.swissmanager.algorithm;

import com.arles.swissmanager.ui.model.Player;

import java.util.ArrayList;
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

    public void setPlayers(List<Player> players) {
        mPlayers = players;
    }

    public int calculateRoundsNumber() {
        return (int) Math.ceil(Math.log(mPlayers.size() + 1) / Math.log(2.0));
    }

    public void createPlayer(String name) {
        Player player = new Player(name);
        mPlayers.add(player);
    }

    public void removePlayer(int position) {
        mPlayers.remove(position);
    }

    public Round startRound() {
        Round round = null;
        boolean ok = ifGameCorrect();
        if (ok) {
            MatchesCreator creator = new MatchesCreator();
            List<Match> matches = creator.createMatchList(mPlayers);
            round = new Round(matches);
        } else {
            // notifyAboutError();
        }
        return round;
    }

    private boolean ifGameCorrect() {
        // bool isAllPAirsFinishRound();
        if (isRoundsNumberNotOver()) {
            nextRound();
            return true;
        }
        return false;
    }

    private boolean isRoundsNumberNotOver() {
        return (mRoundNumber < calculateRoundsNumber());
    }

    private void nextRound() {
        mRoundNumber++;
    }

    public void endRound(Round round) {
        setUnplayedMatchAsLost(round.getMatches());
        mRounds.add(round);
    }

    private void setUnplayedMatchAsLost(List<Match> matches) {
        for (Match match : matches) {
            if (match.getResult() == null) {
                match.reportResult(Points.LOSE, Points.LOSE);
            }
        }
    }

    public int getRoundNumber() {
        return mRoundNumber;
    }

    public Player defineWinner() {
        Player winner = null;
        if (!isRoundsNumberNotOver()) {
            Sorter.sortByPrestige(mPlayers);
            winner = mPlayers.get(0);
        }
        return winner;
    }
}
