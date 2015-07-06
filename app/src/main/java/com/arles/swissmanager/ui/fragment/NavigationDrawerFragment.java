package com.arles.swissmanager.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.Activity;

import com.arles.swissmanager.R;
import com.arles.swissmanager.algorithm.Match;
import com.arles.swissmanager.ui.model.Player;
import com.arles.swissmanager.ui.presenter.NavigationDrawerPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Fragment created to implement navigation draggable panel.
 * This Fragment uses Model View Presenter implementation to implement all the presentation logic.
 * Review NavigationDrawerPresenter to get more info about implementation.
 * <p/>
 * Created by Admin on 06.05.2015.
 */
public class NavigationDrawerFragment extends BaseFragment  {

    @Inject
    NavigationDrawerPresenter mNavDrawerPresenter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavDrawerPresenter.setView(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_navigation_drawer;
    }

    public void setUp(View navigationView, DrawerLayout drawerLayout, final Toolbar toolbar) {
        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

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
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
    }
/*
    @Inject
    NavigationDrawerPresenter mNavDrawerPresenter;
    private INavigationDrawerListener mCallback;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (INavigationDrawerListener) activity;

        } catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString() + "must implement INavigationDrawerListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavDrawerPresenter.setView(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_navigation_drawer;
    }

    public void setUp(View navigationView, DrawerLayout drawerLayout, final Toolbar toolbar) {
        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

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
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
    }

    @OnClick(R.id.ll_sort_by_prestige)
    public void onSortItemClick() {
        mNavDrawerPresenter.sortByPrestige();
    }

    @OnClick(R.id.ll_make_pairs)
    public void onMakePairsItemClick() {
        mNavDrawerPresenter.makePlayerPairs();
    }

    @Override
    public List<Player> getDataFromActivity() {
        return mCallback.getPlayersDataListener();
    }

    @Override
    public void sendDataToActivity(List<Player> list) {
        mCallback.sendPlayerDataListener(list);
    }


     /// INavigationDrawerListener created to allow a Fragment to communicate up to its Activity.

    public interface INavigationDrawerListener {
        List<Player> getPlayersDataListener();
        void sendPlayerDataListener(List<Player> list);
    }*/
}
