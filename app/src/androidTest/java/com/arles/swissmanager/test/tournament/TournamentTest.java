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

package com.arles.swissmanager.test.tournament;

import com.arles.swissmanager.tournament.Match;
import com.arles.swissmanager.tournament.Player;
import com.arles.swissmanager.tournament.Points;
import com.arles.swissmanager.tournament.Round;
import com.arles.swissmanager.tournament.State;
import com.arles.swissmanager.tournament.Tournament;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Admin on 03.08.2015.
 */
public class TournamentTest {

    Tournament mTournament;
    final String[] NAMES_LIST = new String[] {"John", "Alex", "David", "Martin", "Brian", "Harry"};

    @Before
    public void setUp() {
        mTournament = Tournament.getInstance();
    }

    /**
     * Tests that players is set correctly
     * Tests trying to set null to player list.
     */
    @Test
    public void testSetPlayers() {
        List<Player> emptyPlayerList = new ArrayList<>();
        mTournament.setPlayerCollection(emptyPlayerList);
        assertTrue("Players list must be empty", mTournament.getPlayerCollection().size() == 0);

        mTournament.setPlayerCollection(null);
        assertTrue("Players list is not null", mTournament.getPlayerCollection().size() == 0);
    }

    /**
     * Tests formula calculation rounds
     */
    @Test
    public void testCalculateRoundNumber() {
        // key - player size, value - expected value
        Map<Integer, Integer> roundNumDependsOnPlayerNum = new HashMap<>();
        roundNumDependsOnPlayerNum.put(0, 0);
        roundNumDependsOnPlayerNum.put(1, 0);
        roundNumDependsOnPlayerNum.put(2, 1);
        roundNumDependsOnPlayerNum.put(3, 3);
        roundNumDependsOnPlayerNum.put(7, 3);
        roundNumDependsOnPlayerNum.put(8, 4);
        roundNumDependsOnPlayerNum.put(20, 4);
        roundNumDependsOnPlayerNum.put(21, 5);
        for(Map.Entry<Integer, Integer> entry : roundNumDependsOnPlayerNum.entrySet()) {
            final int testData = entry.getKey();
            final int expected = entry.getValue();
            final int actual = mTournament.calculateRoundsNumber(testData);
            assertEquals(expected, actual);
        }
//        for (Iterator iterator = roundNumDependsOnPlayerNum.keySet().iterator(); iterator.hasNext(); ) {
//            final int testData = (int) iterator.next();
//            final int expected = (int) roundNumDependsOnPlayerNum.get(testData);
//            final int actual = mTournament.calculateRoundsNumber(testData);
//            assertEquals(expected, actual);
//        }
    }

    /**
     * Tests the validity of gameplay
     */
    @Test
    public void testGameProcess() {
        // set test player data
        mTournament.setPlayerCollection(getTestPlayersData());

        int roundNumber = mTournament.calculateRoundsNumber(mTournament.getPlayersCount());
        for (int i = 0; i < roundNumber; ++i) {
            Round round = mTournament.createNewRound();
            assertTrue("State of new round must be CREATED", round.getState() == State.CREATED);

            // tests can't create new round because not all rounds are completed
            Round falseRound = mTournament.createNewRound();
            assertEquals("Instance must be null", null, falseRound);

            round.startRound();
            assertTrue("State of starting round must be RUNNING", round.getState() == State.RUNNING);

            randomiseResults(round.getMatches());
            round.endRound();
            assertTrue("State of ending round must be COMPLETED", round.getState() == State.COMPLETED);

            // tests that ended round can't be started again
            round.startRound();
            assertTrue("State of ended round must be COMPLETED", round.getState() == State.COMPLETED);
        }
        // tests can't create one more round because game is over at this point
        Round round = mTournament.createNewRound();
        assertEquals("Instance must be null", null, round);

    }

    private List<Player> getTestPlayersData() {
        List<Player> mDataList = new ArrayList<>();
        for(String name : NAMES_LIST) {
            mDataList.add(new Player(name));
        }
        return mDataList;
    }

    private void randomiseResults(List<Match> matches) {
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
