package com.arles.swissmanager.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arles.swissmanager.R;
import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.algorithm.Tournament;
import com.arles.swissmanager.ui.adapter.ViewPagerAdapter;
import com.arles.swissmanager.ui.fragment.FragmentSwitchListener;
import com.arles.swissmanager.ui.presenter.TourneyPresenter;
import com.arles.swissmanager.ui.presenter.UIModule;
import com.arles.swissmanager.ui.tab.SlidingTabLayout;
import com.arles.swissmanager.utils.ToastUtil;

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

        Tournament tournament = Tournament.getInstance();
        tournament.setPlayers(SwissManagerApplication.getTestPlayersData());
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
            case R.id.action_start_round:
                mPresenter.startRound();
                //item.setEnabled(false);
                break;
            case R.id.action_end_round:
                break;
            case R.id.action_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setViewComponent() {
        setToolbar();
        setSlidingTabLayout();
    }

    private void setViewPagerListener() {
        mTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                FragmentSwitchListener fragment = (FragmentSwitchListener) mPagerAdapter.instantiateItem(mPager, position);
                if (fragment != null) {
                    fragment.onVisible();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void showRoundAddedMessage() {
        String result = getString(R.string.result_round_added);
        ToastUtil.showShortMessage(result, this);
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
        setViewPagerListener();
    }

    private void setToolbar() {
        // found without this function toolbar doesn't show action items
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
