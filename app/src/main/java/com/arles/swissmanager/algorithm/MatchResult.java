package com.arles.swissmanager.algorithm;

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

    static {
        Set<MatchResult> set = new HashSet<>();
        set.add(new MatchResult(Points.WIN, Points.LOSE));
        set.add(new MatchResult(Points.LOSE, Points.WIN));
        set.add(new MatchResult(Points.DRAW, Points.DRAW));
        set.add(new MatchResult(Points.LOSE, Points.LOSE));
        set.add(new MatchResult(Points.DRAW, Points.LOSE));
        set.add(new MatchResult(Points.LOSE, Points.DRAW));
        mSet = Collections.unmodifiableSet(set);
    }

    public MatchResult(Points score1, Points score2) {
        mPlayer1score = score1;
        mPlayer2score = score2;
    }

    public Points getPlayer1score() {
        return mPlayer1score;
    }

    public Points getPlayer2score() {
        return mPlayer2score;
    }

    /**
     * Check if obtained result is correct, e.g. it can not be two winner results
     */
    public static boolean isResultCorrect(Points score1, Points score2) {
        return (mSet.contains(new MatchResult(score1, score2)));
    }

    @Override
    public boolean equals(Object obj) {
//        if (obj == this) {
//            return true;
//        }
//        if (obj == null || obj.getClass() != this.getClass()) {
//            return false;
//        }
//
//        MatchResult other = (MatchResult) obj;
//        return (mPlayer1score == other.mPlayer1score
//                || (mPlayer1score != null && mPlayer1score.equals(other.getPlayer1score())))
//                && (mPlayer2score == other.mPlayer2score
//                || (mPlayer2score != null && mPlayer2score.equals(other.getPlayer2score())));
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
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((mPlayer1score == null) ? 0 : mPlayer1score.hashCode());
//        result = prime * result + ((mPlayer2score == null) ? 0 : mPlayer2score.hashCode());
//        return result;
        return new HashCodeBuilder(17, 31)
                .append(mPlayer1score)
                .append(mPlayer2score)
                .toHashCode();
    }
}
