package com.arles.swissmanager.tournament;

import android.support.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Admin on 23.03.2015.
 */
public class Player implements Comparable<Player> {


    private UUID mUuid;
    private String mName;
    private int mPrestige;
    private boolean mHasBye;
    private Set<Player> mRivals = new HashSet<>();

    private int mSheduledForRound = 1;
    private int mGamesFor;
    private int mGamesAgainst;

    public Player(String name) {
        mUuid = UUID.randomUUID();
        mName = name;
        mPrestige = 0;
    }

    public UUID getUuid() {
        return mUuid;
    }

    public String getName() {
        return mName;
    }

    public int getPrestige() {
        return mPrestige;
    }

    public Set<Player> getRivals() {
        return mRivals;
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
        mPrestige += Points.WIN.get();
    }

    public void lost() {
        mPrestige += Points.LOSE.get();
    }

    public void draw() {
        mPrestige += Points.DRAW.get();
    }

    public void bye() {
        mSheduledForRound++;
        mHasBye = true;
        mPrestige += Points.BYE.get();
    }

    public boolean hadBye() {
        return mHasBye;
    }

    public boolean hadPlayed(Player player) {
        //return mRivals.contains(player);
        UUID uuid = player.getUuid();
        for(Player rival : mRivals) {
            if(rival.getUuid() == uuid) {
                return true;
            }
        }
        return false;
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
