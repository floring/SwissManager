/*
 * Copyright (C) 2015 Arles. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
