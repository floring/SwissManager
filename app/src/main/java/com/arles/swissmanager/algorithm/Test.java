package com.arles.swissmanager.algorithm;

import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.ui.model.Player;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 30.06.2015.
 */
public class Test {

    public static void main() {
        List<Player> players = SwissManagerApplication.getTestPlayersData();

        Tournament tournament = Tournament.getInstance();
        tournament.setPlayers(players);
        int roundNumber = tournament.calculateRoundsNumber(tournament.getPlayersCount());
        for (int i = 0; i < roundNumber; ++i) {
            Round currRound = tournament.createNewRound();
            currRound.startRound();

            randomiseResults(currRound.getMatches());

            currRound.endRound();
        }
        Collection sortedPlayers = tournament.sortPlayersByPrestige();
    }

    private static void randomiseResults(List<Match> matches) {
        for(Match m : matches) {
            int r = new Random().nextInt(6);
            switch (r) {
                case 0:
                    m.reportResult(Points.WIN, Points.LOSE);
                    break;
                case 1:
                    m.reportResult(Points.LOSE, Points.WIN);
                    break;
                case 2:
                    m.reportResult(Points.DRAW, Points.DRAW);
                    break;
                case 3:
                    m.reportResult(Points.LOSE, Points.LOSE);
                    break;
                case 4:
                    m.reportResult(Points.DRAW, Points.LOSE);
                    break;
                case 5:
                    m.reportResult(Points.LOSE, Points.DRAW);
                    break;
            }
        }
    }
}
