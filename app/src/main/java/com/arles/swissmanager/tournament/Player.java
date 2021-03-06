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

package com.arles.swissmanager.tournament;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

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
    private int mGamesFor;
    private int mGamesAgainst;

    public Player(String name) {
        mUuid = UUID.randomUUID();
        mName = name;
        mPrestige = 0;
    }

    @VisibleForTesting
    public Player(String name, int prestige, boolean hasBye) {
        mUuid = UUID.randomUUID();
        mName = name;
        mPrestige = prestige;
        mHasBye = hasBye;
    }

    @VisibleForTesting
    public void setRival(Set<Player> rivals) {
        mRivals.addAll(rivals);
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
        mPrestige += Points.Win.get();
    }

    public void lost() {
        mPrestige += Points.Lose.get();
    }

    public void draw() {
        mPrestige += Points.Draw.get();
    }

    public void bye() {
        mHasBye = true;
        mPrestige += Points.Bye.get();
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
        mRivals.add(player);
    }

    @Override
    public int compareTo(@NonNull Player another) {
        return (mPrestige == another.getPrestige()) ? (another.getGamesFor() - mGamesFor) : (another.getPrestige() - mPrestige);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(mName)
                .toString();
    }
}

