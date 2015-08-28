package com.arles.swissmanager.test.tournament;

import com.arles.swissmanager.tournament.Match;
import com.arles.swissmanager.tournament.MatchResult;
import com.arles.swissmanager.tournament.Player;
import com.arles.swissmanager.tournament.Points;
import com.arles.swissmanager.tournament.Report;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Admin on 04.08.2015.
 */
public class MatchTest {

    private Match mMatch;

    @Before
    public void setUp() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        mMatch = new Match(player1, player2);
    }

    /**
     * Tests method returns report "invalid" when we try to pass invalid match result combination.
     */
    @Test
    public void testReportResult_ReturnInvalid() {
        Set<MatchResult> invalidResults = setInvalidResultsCombination();
        for(MatchResult result : invalidResults) {
            Report report = mMatch.reportResult(result.getPlayer1score(), result.getPlayer2score());
            assertEquals("Report must be Invalid", Report.INVALID_RESULT, report);
        }
    }

    /**
     * Tests method returns report "ok" when we try to pass correct match result combination.
     */
    @Test
    public void testReportResult_ReturnOk() {
        Set<MatchResult> correctResults = setCorrectResultsCombination();
        for(MatchResult result : correctResults) {
            Report report = mMatch.reportResult(result.getPlayer1score(), result.getPlayer2score());
            assertEquals("Report must be Ok", Report.OK, report);
        }
    }


    /**
     * Set invalid results combination for test.
     * For example, players can not both be winners etc.
     */
    private Set<MatchResult> setInvalidResultsCombination() {
        return new HashSet<MatchResult>() {{
            add(new MatchResult(Points.Win, Points.Win));
            add(new MatchResult(Points.Win, Points.Draw));
            add(new MatchResult(Points.Draw, Points.Win));
        }};
    }

    /**
     * Set valid results combination for test.
     */
    private Set<MatchResult> setCorrectResultsCombination() {
        return new HashSet<MatchResult>() {{
            add(new MatchResult(Points.Win, Points.Lose));
            add(new MatchResult(Points.Lose, Points.Win));
            add(new MatchResult(Points.Draw, Points.Draw));
            add(new MatchResult(Points.Lose, Points.Lose));
            add(new MatchResult(Points.Draw, Points.Lose));
            add(new MatchResult(Points.Lose, Points.Draw));
        }};
    }
}
