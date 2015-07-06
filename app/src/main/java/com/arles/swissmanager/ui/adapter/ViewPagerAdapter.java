package com.arles.swissmanager.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arles.swissmanager.ui.fragment.PlayerTabFragment;
import com.arles.swissmanager.ui.fragment.RoundTabFragment;

/**
 * Created by Admin on 06.07.2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private CharSequence mTitles[];
    private int mTabsNumber;

    public ViewPagerAdapter(FragmentManager fm, CharSequence titles[], int tabNum) {
        super(fm);
        mTitles = titles;
        mTabsNumber = tabNum;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) // if the position is 0 we are returning the First tab
        {
            PlayerTabFragment playerTabFragment = new PlayerTabFragment();
            return playerTabFragment;
        } else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            RoundTabFragment roundTabFragment = new RoundTabFragment();
            return roundTabFragment;
        }
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