package com.arles.swissmanager.test.algorithm;

import com.arles.swissmanager.algorithm.Match;
import com.arles.swissmanager.algorithm.Player;
import com.arles.swissmanager.algorithm.Points;
import com.arles.swissmanager.algorithm.Round;
import com.arles.swissmanager.algorithm.State;
import com.arles.swissmanager.algorithm.Tournament;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
        mTournament.setPlayers(emptyPlayerList);
        assertTrue("Players list must be empty", mTournament.getPlayerCollection().size() == 0);

        mTournament.setPlayers(null);
        assertTrue("Players list is not null", mTournament.getPlayerCollection().size() == 0);
    }

    /**
     * Tests formula calculation rounds
     */
    @Test
    public void testCalculateRoundNumber() {
        // key - player size, value - expected value
        Map roundNumberData = new HashMap();
        roundNumberData.put(0, 0);
        roundNumberData.put(1, 0);
        roundNumberData.put(2, 1);
        roundNumberData.put(3, 3);
        roundNumberData.put(7, 3);
        roundNumberData.put(8, 4);
        roundNumberData.put(20, 4);
        roundNumberData.put(21, 5);
        for (Iterator iterator = roundNumberData.keySet().iterator(); iterator.hasNext(); ) {
            final int testData = (int) iterator.next();
            final int expected = (int) roundNumberData.get(testData);
            final int actual = mTournament.calculateRoundsNumber(testData);
            assertEquals(expected, actual);
        }
    }

    /**
     * Tests the validity of gameplay
     */
    @Test
    public void testGameProcess() {
        // set test player data
        mTournament.setPlayers(getTestPlayersData());

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
