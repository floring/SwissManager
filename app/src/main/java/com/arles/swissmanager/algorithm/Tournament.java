package com.arles.swissmanager.algorithm;

import com.arles.swissmanager.ui.model.Player;

import java.util.ArrayList;
import java.util.Collection;
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
        return (int) (Math.ceil(Math.log(mPlayers.size())) +
                Math.ceil(Math.log(Math.min(2.0, mPlayers.size() - 1))));
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
        if (isGameCorrect()) {
            nextRound();
            MatchesCreator creator = new MatchesCreator();
            List<Match> matches = creator.createMatchList(mPlayers);
            round = createRound(matches);
        } else {
            // notifyAboutError();
        }
        return round;
    }

    private boolean isGameCorrect() {
        return  (isAllRoundsCompleted() && isRoundsNumberNotOver());
    }

    private boolean isRoundsNumberNotOver() {
        return (mRoundNumber < calculateRoundsNumber());
    }

    private boolean isAllRoundsCompleted() {
        for (Round round : mRounds) {
            if(round.state != State.COMPLETED) {
                return false;
            }
        }
        return true;
    }

    private void nextRound() {
        mRoundNumber++;
    }

    private Round createRound(List<Match> matches) {
        Round round = new Round(mRoundNumber, matches);
        mRounds.add(round);
        return round;
    }

    public void endRound(Round round) {
        setUnplayedMatchAsLost(round.getMatches());
    }

    private void setUnplayedMatchAsLost(List<Match> matches) {
        for (Match match : matches) {
            if (match.getResult() == null) {
                match.reportResult(Points.LOSE, Points.LOSE);
            }
        }
    }

    public int getCurrentRoundNumber() {
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

    public Collection sortPlayersByPrestige() {
        Sorter.sortByPrestige(mPlayers);
        return mPlayers;
    }

    public List<Round> getRoundCollection() {
        return mRounds;
    }

    public List<Player> getPlayerCollection() {
        return mPlayers;
    }
}
