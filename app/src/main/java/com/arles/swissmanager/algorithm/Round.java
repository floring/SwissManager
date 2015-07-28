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
    public State state;

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

    public void updateMatches(List<Match> list) {
        mMatches = list;
    }
}
