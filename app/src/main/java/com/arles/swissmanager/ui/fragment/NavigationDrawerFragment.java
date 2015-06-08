package com.arles.swissmanager.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.presenter.NavigationDrawerPresenter;

import javax.inject.Inject;

/**
 * Fragment created to implement navigation draggable panel.
 * This Fragment uses Model View Presenter implementation to implement all the presentation logic.
 * Review NavigationDrawerPresenter to get more info about implementation.
 *
 * Created by Admin on 06.05.2015.
 *
 */
public class NavigationDrawerFragment extends BaseFragment implements NavigationDrawerPresenter.INavigationView {

    private ActionBarDrawerToggle mDrawerToggle;

    @Inject
    NavigationDrawerPresenter navDrawerPresenter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navDrawerPresenter.setView(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_navigation_drawer;
    }

    public void setUp(View navigationView, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        //drawerLayout.openDrawer(navigationView);
        drawerLayout.setDrawerListener(mDrawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

}
