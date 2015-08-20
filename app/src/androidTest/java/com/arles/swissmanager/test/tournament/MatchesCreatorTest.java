package com.arles.swissmanager.test.tournament;

import com.arles.swissmanager.tournament.Match;
import com.arles.swissmanager.tournament.MatchesCreator;
import com.arles.swissmanager.tournament.Player;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

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

    /**
     * Tests players matching.
     * Tests that each player in players list has a pair and he has not played it yet.
     */
    @Test
    public void testMatching_ReturnsMatches() {
        List<Player> copyPlayers = setListWith6Players();

        mPlayerList = new ArrayList<>(copyPlayers);
        List<Match> actualMatchList = mCreator.createMatchList(mPlayerList);
        assertEquals("Size must be 6;", mPlayerList.size(), actualMatchList.size());

        for (Player player : copyPlayers) {
            if (hasCorrectPair(player, actualMatchList)) {
                continue;
            }
            fail("Incorrect matching;");
        }
    }

    private boolean hasCorrectPair(Player player, List<Match> matchList) {
        for (Match match : matchList) {
            if(match.getPlayer1().equals(player)) {
                Player chosenRival = match.getPlayer2();
                if(!player.hadPlayed(chosenRival)) {
                    return true;
                }
            }
        }
        return false;
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

    private List<Player> setListWith6Players() {
        List<Player> dataList = new ArrayList<>();
        final Player p1 = new Player("John", 2, false);
        final Player p2 = new Player("Alex", 1, false);
        final Player p3 = new Player("David", 6, false);
        final Player p4 = new Player("Martin", 2, false);
        final Player p5 = new Player("Brian", 3, false);
        final Player p6 = new Player("Harry", 3, false);

        Set<Player> list = new HashSet<>();

        list.add(p2);
        list.add(p5);
        p1.setRival(list);

        list.clear();
        list.add(p4);
        list.add(p1);
        p2.setRival(list);

        list.clear();
        list.add(p4);
        list.add(p6);
        p3.setRival(list);

        list.clear();
        list.add(p2);
        list.add(p3);
        p4.setRival(list);

        list.clear();
        list.add(p1);
        list.add(p6);
        p5.setRival(list);

        list.clear();
        list.add(p3);
        list.add(p5);
        p6.setRival(list);

        dataList.add(p1);
        dataList.add(p2);
        dataList.add(p3);
        dataList.add(p4);
        dataList.add(p5);
        dataList.add(p6);

        return dataList;
    }

}
