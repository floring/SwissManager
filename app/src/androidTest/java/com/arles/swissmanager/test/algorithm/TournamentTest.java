package com.arles.swissmanager.test.algorithm;

import com.arles.swissmanager.algorithm.Tournament;
import com.arles.swissmanager.ui.model.Player;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Admin on 03.08.2015.
 */
public class TournamentTest {

    Tournament mTournament;

    @Before
    public void setUp() {
        mTournament = Tournament.getInstance();
    }

    @Test
    public void testSetPlayers() {
        List<Player> emptyPlayerList = new ArrayList<>();
        mTournament.setPlayers(emptyPlayerList);
        assertTrue("Players list must be empty", mTournament.getPlayerCollection().size() == 0);

        mTournament.setPlayers(null);
        assertTrue("Players list is not null must be empty", mTournament.getPlayerCollection().size() == 0);
    }

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

    @Test
    public void testCreatePlayer() {

    }
}
