package com.arles.swissmanager.tournament;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Defines possible match results.
 * Created by Admin on 01.07.2015.
 */
public class MatchResult {
    private Points mPlayer1score;
    private Points mPlayer2score;
    private static final Set<MatchResult> mSet;

    public Points getPlayer1score() {
        return mPlayer1score;
    }

    public Points getPlayer2score() {
        return mPlayer2score;
    }

    static {
        Set<MatchResult> set = new HashSet<>();
        set.add(new MatchResult(Points.Win, Points.Lose));
        set.add(new MatchResult(Points.Lose, Points.Win));
        set.add(new MatchResult(Points.Draw, Points.Draw));
        set.add(new MatchResult(Points.Lose, Points.Lose));
        set.add(new MatchResult(Points.Draw, Points.Lose));
        set.add(new MatchResult(Points.Lose, Points.Draw));
        mSet = Collections.unmodifiableSet(set);
    }

    public MatchResult(Points score1, Points score2) {
        mPlayer1score = score1;
        mPlayer2score = score2;
    }

    /**
     * Check if obtained result is correct, e.g. it can not be two winner results
     */
    public static boolean isResultCorrect(Points score1, Points score2) {
        return (mSet.contains(new MatchResult(score1, score2)));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MatchResult))
            return false;
        if (obj == this)
            return true;

        MatchResult other = (MatchResult) obj;
        return new EqualsBuilder()
                .append(mPlayer1score, other.mPlayer1score)
                .append(mPlayer2score, other.mPlayer2score)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(mPlayer1score)
                .append(mPlayer2score)
                .toHashCode();
    }
}
