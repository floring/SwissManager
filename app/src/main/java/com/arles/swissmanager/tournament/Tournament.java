package com.arles.swissmanager.tournament;

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
        if(players != null) {
            mPlayers = players;
        }
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
            List<Match> matches = creator.createMatchList(mPlayers);
            round = createRoundInstance(mRoundNumber, matches);
        } else {
            // notifyAboutError();
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

    private Round createRoundInstance(int roundNumber, List<Match> matches) {
        Round round = new Round(roundNumber, matches);
        mRounds.add(round);
        return round;
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

    public int getPlayersCount() {
        return (mPlayers != null) ? mPlayers.size() : 0;
    }
}
