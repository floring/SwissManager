package com.arles.swissmanager.tournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MatchCreator creates matches between players based on Swiss System Ordering.
 * Created by Admin on 08.06.2015.
 */
public class MatchesCreator {

    private List<PlayersPair> mPairs;
    private Player mByePlayer;

    public List<Match> createMatchList(List<Player> playerList) {
        List<PlayersPair> playersPairs = getMatching(playerList);

        List<Match> matchList = new ArrayList<>();
        if(playersPairs != null) {
            matchList = makeMatches(playersPairs);
            if(!matchList.isEmpty()) {
                setByePlayer();
            }
        }
        return matchList;
    }

    private List<PlayersPair> getMatching(List<Player> playerList) {
        Player currentPlayer = peekPlayerWithMaxPrestige(playerList);

        if (playerList.isEmpty()) {
            if (currentPlayer.hadBye()) {
                playerList.add(0, currentPlayer);
                return null;
            } else {
                mByePlayer = currentPlayer;
                mPairs = new ArrayList<>();
                return mPairs;
            }
        }

        for (int i = 0; i < playerList.size(); ++i) {
            Player currentRival = playerList.get(i);
            if (currentPlayer.hadPlayed(currentRival)) {
                continue;
            }
            playerList.remove(i);

            if (playerList.isEmpty()) {
                mPairs = new ArrayList<>();
                mPairs.add(new PlayersPair(currentPlayer, currentRival));
                return mPairs;
            }

            mPairs = getMatching(playerList);
            if (mPairs == null) {
                playerList.add(i, currentRival);
            } else {
                mPairs.add(new PlayersPair(currentPlayer, currentRival));
                return mPairs;
            }
        }
        if (currentPlayer != null) {
            playerList.add(0, currentPlayer);
        }

        return null;
    }

    private Player peekPlayerWithMaxPrestige(List<Player> playerList) {
        Collections.sort(playerList);
        if (playerList.isEmpty()) {
            return null;
        }
        Player playerWithMaxPrestige = playerList.get(0);
        playerList.remove(playerWithMaxPrestige);
        return playerWithMaxPrestige;
    }

    public Player getByePlayer() {
        return mByePlayer;
    }

    private List<Match> makeMatches(List<PlayersPair> playersPairs) {
        List<Match> matchList = new ArrayList<>();
        for (PlayersPair pair : playersPairs) {
            matchList.add(new Match(pair.getPlayer1(), pair.getPlayer2()));
            matchList.add(new Match(pair.getPlayer2(), pair.getPlayer1()));
        }
        return matchList;
    }

    private void setByePlayer() {
        if (mByePlayer != null) {
            mByePlayer.bye();
        }
    }

    /**
     * Inner class that represents players pair, e.g. for matches
     * Created to simplify code structure.
     */
    protected class PlayersPair {
        private Player mPlayer1;
        private Player mPlayer2;

        public PlayersPair(Player player1, Player player2) {
            mPlayer1 = player1;
            mPlayer2 = player2;
        }

        public Player getPlayer1() {
            return mPlayer1;
        }

        public Player getPlayer2() {
            return mPlayer2;
        }
    }
}
