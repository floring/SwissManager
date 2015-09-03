package com.arles.swissmanager.ui.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arles.swissmanager.R;
import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.tournament.Tournament;
import com.arles.swissmanager.ui.adapter.ViewPagerAdapter;
import com.arles.swissmanager.ui.fragment.FragmentParentClickListener;
import com.arles.swissmanager.ui.presenter.TourneyPresenter;
import com.arles.swissmanager.ui.presenter.UIModule;
import com.arles.swissmanager.ui.tab.SlidingTabLayout;
import com.arles.swissmanager.utils.ToastUtil;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Main activity for application.
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

        mPresenter.setTourneySettingsFromPrefs();

        Tournament tour = Tournament.getInstance();
        tour.setPlayerCollection(SwissManagerApplication.getTestPlayersData());
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
            case R.id.action_create_new_round:
                mPresenter.createNewRoundAction();
                break;
            case R.id.action_settings:
                mPresenter.openPreferences();
                break;
            case R.id.action_total_round_number:
                mPresenter.getTotalRoundNumber();
                break;
            case R.id.action_sort_by_prestige:
                mPresenter.sortByPrestige();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setViewComponent() {
        setToolbar();
        setSlidingTabLayout();
    }

    @Override
    public void showRoundMessage(String result) {
        ToastUtil.showShortMessage(result, this);
    }

    @Override
    public void showDialog(CharSequence content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(content);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void refreshFragmentData(int position) {
        FragmentParentClickListener fragment = (FragmentParentClickListener) mPagerAdapter.getItem(position);
        if (fragment != null) {
            fragment.onRefresh();
        }
    }

    @Override
    public int getPagerAdapterPlayerTabPosition() {
        return ViewPagerAdapter.PLAYER_TAB_POSITION;
    }

    @Override
    public int getPagerAdapterRoundTabPosition() {
        return ViewPagerAdapter.ROUND_TAB_POSITION;
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

    private void setToolbar() {
        // found without this function toolbar doesn't show action items
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
