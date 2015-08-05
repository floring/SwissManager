package com.arles.swissmanager.tournament;

import java.util.Collections;
import java.util.List;

/**
 * Sorter class provides methods to sort players in a different order.
 * Created by Admin on 01.07.2015.
 */
public class Sorter {

    public static List<Player> sortByPrestige(List<Player> players) {
        Collections.sort(players);
        return players;
    }

    public static int[] sortBySwissSystem(List<Player> players) {
        int playersNum = players.size();
        int diff = 0;
        boolean hadPlayed = true;
        int[] optimalPlayersOrder = new int[playersNum];
        int optimalOrderHealth = Integer.MAX_VALUE;

        PermutationGenerator pg = new PermutationGenerator(playersNum);
        while (pg.hasMore()) {
            int[] currPlayersOrder = pg.getNextPermutation();

            for (int i = 0; i < playersNum / 2; i++) {
                // if players have already played
                if (players.get(currPlayersOrder[i * 2]).hadPlayed(players.get(currPlayersOrder[i * 2 + 1]))) {
                    hadPlayed = false;
                }
                // otherwise - add differences
                if (hadPlayed) {
                    diff += Math.abs(players.indexOf(players.get(currPlayersOrder[i * 2])) -
                            players.indexOf(players.get(currPlayersOrder[i * 2 + 1])));
                }
            }
            if (playersNum % 2 != 0) {
                // if last player did not have bye
                if (players.get(currPlayersOrder[playersNum - 1]).hadBye()) {
                    hadPlayed = false;
                } else {
                    diff += playersNum - players.indexOf(players.get(currPlayersOrder[playersNum - 1]));
                }
            }
            // if current order is better than previous, then save this order as optimal
            if (hadPlayed && diff > 0 && diff < optimalOrderHealth) {
                optimalOrderHealth = diff;
                optimalPlayersOrder = currPlayersOrder.clone();
            }
            // reset
            diff = 0;
            hadPlayed = true;
        }
        return optimalPlayersOrder;
    }
}
