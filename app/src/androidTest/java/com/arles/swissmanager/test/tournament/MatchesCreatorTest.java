package com.arles.swissmanager.test.tournament;

import com.arles.swissmanager.tournament.Match;
import com.arles.swissmanager.tournament.MatchesCreator;
import com.arles.swissmanager.tournament.Player;
import com.arles.swissmanager.tournament.Points;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Tests pairs matching and creation of match list on different number of players.
 * Created by Admin on 15.08.2015.
 */
public class MatchesCreatorTest {

    private MatchesCreator mCreator;
    private List<Player> mPlayerList = new ArrayList<>();
    private List<Match> mExpectedMatchList = new ArrayList<>();

    @Before
    public void setUp() {
        mCreator = new MatchesCreator();
    }

    /**
     * Tests attempt to pair list with one player.
     * Must returns empty match list.
     */
    @Test
    public void test1playerMatching_ReturnEmpty() {
        setListWith1Player();

        List<Match> actualMatchList = mCreator.createMatchList(mPlayerList);
        assertEquals("Result must be empty list;", mExpectedMatchList, actualMatchList);
    }

    /**
     * Tests attempt to pair 2 player.
     * Must returns pair between this players.
     */
    @Test
    public void test2players_ReturnsMatches() {
        setListWith2Players();

        List<Match> actualMatchList = mCreator.createMatchList(mPlayerList);
        assertEquals("Size must be 2;", mPlayerList.size(), actualMatchList.size());
    }

    @Test
    public void testByeOn3players_ReturnsMatches() {
        setListWith3Players();

        // sort collection by prestige
        Collections.sort(mPlayerList);
        List<Match> actualMatchList = mCreator.createMatchList(mPlayerList);
        assertEquals("Size must be 2;", mPlayerList.size() - 1, actualMatchList.size());

        // get the last in list
        Player expectedPlayerBye = mPlayerList.get(mPlayerList.size() - 1);
        assertEquals(expectedPlayerBye, mCreator.getByePlayer());
    }

    private void setListWith1Player() {
        Player p1 = new Player("John");
        mPlayerList.add(p1);

        mExpectedMatchList = new ArrayList<>();
    }

    private void setListWith2Players() {
        Player p1 = new Player("John");
        Player p2 = new Player("Alex");
        mPlayerList.add(p1);
        mPlayerList.add(p2);
    }

    private void setListWith3Players() {
        Player p1 = new Player("John");
        Player p2 = new Player("Alex");
        Player p3 = new Player("David");
        mPlayerList.add(p1);
        mPlayerList.add(p2);
        mPlayerList.add(p3);
    }

}
