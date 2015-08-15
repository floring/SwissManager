package com.arles.swissmanager.test.tournament;

import com.arles.swissmanager.tournament.Match;
import com.arles.swissmanager.tournament.MatchesCreator;
import com.arles.swissmanager.tournament.Player;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Tests pairs matching and creation of match list on different number of players.
 * Created by Admin on 15.08.2015.
 */
public class MatchesCreatorTest {

    private MatchesCreator mCreator;
    private List<Player> mPlayerList = new ArrayList<>();

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
        List<Match> expectedMatchList = new ArrayList<>();
        assertEquals("Result must be empty list", expectedMatchList, actualMatchList);
    }

    /**
     * Tests attempt to pair 2 player.
     * Must returns pair between this players.
     */
    @Test
    public void test2players_ReturnsPair() {
    }

    private void setListWith1Player() {
        Player player = new Player("John");
        mPlayerList.add(player);
    }

}
