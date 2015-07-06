package com.arles.swissmanager.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.adapter.ViewPagerAdapter;
import com.arles.swissmanager.ui.fragment.NavigationDrawerFragment;
import com.arles.swissmanager.ui.presenter.TourneyPresenter;
import com.arles.swissmanager.ui.presenter.UIModule;
import com.arles.swissmanager.ui.tab.SlidingTabLayout;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by Admin on 02.07.2015.
 */
public class TourneyActivity extends BaseActivity implements TourneyPresenter.IView {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.view_pager)
    ViewPager mPager;
    @InjectView(R.id.tabs)
    SlidingTabLayout mTabLayout;
    @Inject
    TourneyPresenter mPresenter;
    ViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourney);
        injectViews();
        mPresenter.setView(this);
        mPresenter.initializeViewComponent();
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new UIModule());
        return modules;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setViewComponent() {
        setToolbar();
        //setUpNavDrawerFragment();
        setSlidingTabLayout();
    }

    private void setSlidingTabLayout() {
        CharSequence[] titles = getResources().getStringArray(R.array.tabs);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, titles.length);
        mPager.setAdapter(mPagerAdapter);
        mTabLayout.setDistributeEvenly(true);
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        mTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.secondaryAccentColor);
            }
        });
        // Setting the ViewPager For the SlidingTabsLayout
        mTabLayout.setViewPager(mPager);
    }

    private void setUpNavDrawerFragment() {
        NavigationDrawerFragment navDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navDrawerFragment.setUp(
                findViewById(R.id.fragment_navigation_drawer),
                (DrawerLayout) findViewById(R.id.drawer_layout),
                mToolbar);
    }

    private void setToolbar() {
        // found without this function toolbar doesn't show action items
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
