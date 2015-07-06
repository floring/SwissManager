package com.arles.swissmanager.ui.model;

import android.support.annotation.NonNull;

import com.arles.swissmanager.algorithm.Points;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.UUID;

/**
 * Created by Admin on 23.03.2015.
 */
public class Player implements Comparable<Player> {


    private UUID mUuid;
    private String mName;
    private int mPrestige;
    private boolean mHasBye;
    private LinkedHashSet<Player> mRivals = new LinkedHashSet<>();

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
        mPrestige += Points.WIN;
    }

    public void lost() {
        mPrestige += Points.LOSE;
    }

    public void draw() {
        mPrestige += Points.MODIFIED_WIN;
    }

    public void bye() {
        mSheduledForRound++;
        mHasBye = true;
        mPrestige += Points.BYE;
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

    @Override
    public int compareTo(@NonNull Player another) {
        return (mPrestige == another.getPrestige()) ? (another.getGamesFor() - mGamesFor) : (another.getPrestige() - mPrestige);
    }
}

