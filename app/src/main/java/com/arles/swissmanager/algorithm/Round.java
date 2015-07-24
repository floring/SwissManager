package com.arles.swissmanager.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents Round entity in game.
 * Created by Admin on 30.06.2015.
 */
public class Round {

    private List<Match> mMatches = new ArrayList<>();

    public Round(List<Match> list) {
        if(list != null) {
            mMatches = list;
        }
    }

    public List<Match> getMatches() {
        return mMatches;
    }

    public void updateMatches(List<Match> list) {
        mMatches = list;
    }
}
