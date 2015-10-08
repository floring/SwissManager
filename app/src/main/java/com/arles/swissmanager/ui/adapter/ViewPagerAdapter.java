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

package com.arles.swissmanager.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arles.swissmanager.ui.fragment.PlayerTabFragment;
import com.arles.swissmanager.ui.fragment.RoundTabFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Providing the adapter to populate pages inside of a ViewPager.
 * Created by Admin on 06.07.2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private CharSequence mTitles[];
    private int mTabsNumber;
    private List<Fragment> mFragment = new ArrayList<Fragment>(Arrays.asList(new PlayerTabFragment(), new RoundTabFragment()));

    public final static int PLAYER_TAB_POSITION = 0;
    public final static int ROUND_TAB_POSITION = 1;

    public ViewPagerAdapter(FragmentManager fm, CharSequence titles[], int tabNum) {
        super(fm);
        mTitles = titles;
        mTabsNumber = tabNum;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    // Return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    // Return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return mTabsNumber;
    }
}