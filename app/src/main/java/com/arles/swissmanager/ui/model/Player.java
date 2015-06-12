package com.arles.swissmanager.ui.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Admin on 23.03.2015.
 */
public class Player implements Comparable<Player> {

    public static final int WIN_POINTS = 2;
    public static final int DRAW_POINTS = 1;
    public static final int BYE_POINTS = 4;
    public static final int LOSE_POINTS = 0;

    private UUID mUuid;
    private String mName;
    private int mPrestige;
    private boolean mHasBye;
    private ArrayList<Player> mRivals = new ArrayList<Player>();

    private int mSheduledForRound = 1;
    private int mGamesFor;
    private int mGamesAgainst;

    public Player(String name) {
        mUuid = UUID.randomUUID();
        mName = name;
        mPrestige = 0;
    }

    public String getName() {
        return mName;
    }

    public int getPrestige() {
        return mPrestige;
    }

    public void setGamesFor(int games) {
        mGamesFor += games;
    }

    public int getGamesFor() {
        return mGamesFor;
    }

    public void setGamesAgainst(int games) {
        mGamesAgainst += games;
    }

    public int getGamesAgainst() {
        return mGamesAgainst;
    }

    public void won() {
        mPrestige += WIN_POINTS;
    }

    public void loss() {
        mPrestige += LOSE_POINTS;
    }

    public void draw() {
        mPrestige += DRAW_POINTS;
    }

    public void bye() {
        mSheduledForRound++;
        mHasBye = true;
        mPrestige += BYE_POINTS;
    }

    public boolean hadBye() {
        return mHasBye;
    }

    public boolean hadPlayed(Player player) {
        return mRivals.contains(player);
    }

    public void playWith(Player player) {
        mSheduledForRound++;
        mRivals.add(player);
    }

    public boolean scheduled(int i) {
        if (i < mSheduledForRound) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Player another) {
        return (mPrestige == another.getPrestige()) ? (another.getGamesFor() - mGamesFor) : (another.getPrestige() - mPrestige);
    }
}

