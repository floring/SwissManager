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
