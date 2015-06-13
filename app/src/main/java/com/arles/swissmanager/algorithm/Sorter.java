package com.arles.swissmanager.algorithm;

import android.support.annotation.NonNull;

import com.arles.swissmanager.ui.model.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Admin on 08.06.2015.
 */
public class Sorter {

    private List<Player> mPlayers = new ArrayList<Player>();
    private List<Match> mMatches = new ArrayList<Match>();

    public Sorter(List<Player> players) {
        mPlayers = players;
    }

    public List<Player> sort() {
        arrangeByPrestige();
        return mPlayers;
    }

    public List<Player> doPairsBySwiss() {
        int[] order = orderBySwissSystem();
        makePairs(order);
        List<Player> playersList = makeListFromPairs();
        return playersList;
    }

    private int[] orderBySwissSystem() {
        int playersNum = mPlayers.size();
        int[] playersOrder;
        int orderHealth = 0;
        boolean hadPlayed = true;
        int[] optimalPlayersOrder = new int[playersNum];
        int optimalOrderHealth = Integer.MAX_VALUE;

        PermutationGenerator pg = new PermutationGenerator(playersNum);
        while (pg.hasMore()) {
            playersOrder = pg.getNext();

            for (int i = 0; i < playersNum / 2; i++) {
                // if players have already played
                if (mPlayers.get(playersOrder[i * 2]).hadPlayed(mPlayers.get(playersOrder[i * 2 + 1]))) {
                    hadPlayed = false;
                }
                // otherwise - add differences
                if (hadPlayed) {
                    orderHealth += Math.abs(mPlayers.indexOf(mPlayers.get(playersOrder[i * 2])) -
                            mPlayers.indexOf(mPlayers.get(playersOrder[i * 2 + 1])));
                }
            }
            // if has already had a bye
            if (playersNum % 2 != 0) {
                if (mPlayers.get(playersOrder[playersNum - 1]).hadBye()) {
                    hadPlayed = false;
                } else {
                    orderHealth += playersNum - mPlayers.indexOf(mPlayers.get(playersOrder[playersNum - 1]));
                }
            }
            // if good, update
            if(hadPlayed && orderHealth > 0 && orderHealth < optimalOrderHealth){
                optimalOrderHealth = orderHealth;
                optimalPlayersOrder = playersOrder.clone();
            }
            // reset
            orderHealth = 0;
            hadPlayed = true;
        }
        return optimalPlayersOrder;
    }

    private List<Player> makeListFromPairs() {
        List<Player> list = new ArrayList<>();
        Match currentPair;
        for(int i = 0; i < mMatches.size(); i++) {
            currentPair = mMatches.get(i);
            list.add(currentPair.getPlayer1());
            list.add(currentPair.getPlayer2());
        }
        return list;
    }

    private void makePairs(int[] order) {
        Player byePlayer;
        int pairsNumber = mPlayers.size() / 2;
        for (int i = 0; i < pairsNumber; i++) {
            mMatches.add(new Match(mPlayers.get(order[i*2]), mPlayers.get(order[i*2+1])));
        }
        if (mPlayers.size() % 2 != 0) {
            byePlayer = mPlayers.get(order[mPlayers.size() - 1]);
            byePlayer.bye();
        }
    }

    private void arrangeByPrestige() {
        Collections.sort(mPlayers);
        mMatches.clear();
    }
/*
    private int getRoundsNumber(int playersNumber) {
        return (int) Math.ceil(Math.log(playersNumber + 1) / Math.log(2.0));
    }

    private boolean isZeroRound() {
        return (mRound == 0);
    }

    private void nextRound() {
        mRound++;
    }

    private void randomiseResults() {
        for (Match m : mMatches) {
            m.score(0, 0);

        }
    }*/
}
