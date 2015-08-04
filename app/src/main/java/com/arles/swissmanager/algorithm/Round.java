package com.arles.swissmanager.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents Round entity in game.
 * Created by Admin on 30.06.2015.
 */
public class Round {

    private int mNumber;
    private List<Match> mMatches = new ArrayList<>();
    private State mState;

    public Round(int number, List<Match> list) {
        mNumber = number;
        if(list != null) {
            mMatches = list;
        }
    }

    public List<Match> getMatches() {
        return mMatches;
    }

    public int getNumber() {
        return  mNumber;
    }

    public State getState() {
        return mState;
    }

    public void setCreatedState() {
        mState = State.CREATED;
    }

    public void startRound() {
        mState = State.RUNNING;
    }

    public void endRound() {
        setUnplayedMatchAsLost(mMatches);
        mState = State.COMPLETED;
    }

    private void setUnplayedMatchAsLost(List<Match> matches) {
        for (Match match : matches) {
            if (match.getResult() == null) {
                match.reportResult(Points.LOSE, Points.LOSE);
            }
        }
    }


}
