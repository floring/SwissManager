package com.arles.swissmanager.algorithm;

import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.ui.model.Player;

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
        int roundNumber = tournament.calculateRoundsNumber();
        for (int i = 0; i < roundNumber; ++i) {
            Round currRound = tournament.startRound();
            randomiseResults(currRound.getMatches());
            tournament.endRound(currRound);
        }
        Player p = tournament.defineWinner();

    }

    private static void randomiseResults(List<Match> matches) {
        for(Match m : matches) {
            int r = new Random().nextInt(6);
            switch (r) {
                case 0:
                    m.reportResult(Points.WIN.get(), Points.LOSE.get());
                    break;
                case 1:
                    m.reportResult(Points.LOSE.get(), Points.WIN.get());
                    break;
                case 2:
                    m.reportResult(Points.DRAW.get(), Points.DRAW.get());
                    break;
                case 3:
                    m.reportResult(Points.LOSE.get(), Points.LOSE.get());
                    break;
                case 4:
                    m.reportResult(Points.DRAW.get(), Points.LOSE.get());
                    break;
                case 5:
                    m.reportResult(Points.LOSE.get(), Points.DRAW.get());
                    break;
            }
        }
    }
}
