package com.arles.swissmanager.tournament;

import java.util.ArrayList;
import java.util.List;

/**
 * MatchCreator creates matches between players based on Swiss System Ordering.
 * Created by Admin on 08.06.2015.
 */
public class MatchesCreator {

    public List<Match> createMatchList(List<Player> players) {
        int[] playersOrder = Sorter.sortBySwissSystem(players);
        List<Match> matches = makeMatches(players, playersOrder);
        return matches;
    }

    private List<Match> makeMatches(List<Player> players, int[] order) {
        Player byePlayer;
        List<Match> matches = new ArrayList<>();

        for (int i = 0; i < players.size() / 2; i++) {
            matches.add(new Match(players.get(order[i * 2]), players.get(order[i * 2 + 1])));
            matches.add(new Match(players.get(order[i * 2 + 1]), players.get(order[i * 2])));
        }
        if (players.size() % 2 != 0) {
            byePlayer = players.get(order[players.size() - 1]);
            byePlayer.bye();
        }
        return matches;
    }
}
