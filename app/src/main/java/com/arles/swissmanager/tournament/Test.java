package com.arles.swissmanager.tournament;

import com.arles.swissmanager.SwissManagerApplication;

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
        tournament.setPlayerCollection(players);
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
                    m.reportResult(Points.Win, Points.Lose);
                    break;
                case 1:
                    m.reportResult(Points.Lose, Points.Win);
                    break;
                case 2:
                    m.reportResult(Points.Draw, Points.Draw);
                    break;
                case 3:
                    m.reportResult(Points.Lose, Points.Lose);
                    break;
                case 4:
                    m.reportResult(Points.Draw, Points.Lose);
                    break;
                case 5:
                    m.reportResult(Points.Lose, Points.Draw);
                    break;
            }
        }
    }
}
