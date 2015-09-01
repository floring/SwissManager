package com.arles.swissmanager.ui.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.arles.swissmanager.R;
import com.arles.swissmanager.tournament.Match;
import com.arles.swissmanager.tournament.Points;
import com.arles.swissmanager.ui.adapter.MatchesAdapter;
import com.arles.swissmanager.ui.presenter.RoundPresenter;
import com.arles.swissmanager.ui.presenter.UIModule;
import com.arles.swissmanager.utils.DividerBoldItemDecoration;
import com.arles.swissmanager.utils.ToastUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import jp.wasabeef.recyclerview.animators.adapters.SlideInBottomAnimationAdapter;

public class RoundActivity extends BaseActivity implements RoundPresenter.IView {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.recycler_view_matches)
    RecyclerView mRecyclerView;
    @Inject
    RoundPresenter mPresenter;
    private MatchesAdapter mAdapter;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        injectViews();
        mPresenter.getPassedExtrasFrom(getIntent());

        mPresenter.setView(this);
        mPresenter.initializeViewComponent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // get matches from algorithm class and set it to adapter
        mAdapter.setData(mPresenter.getMatchesData());
    }

    @Override
    protected void onPause() {
        super.onPause();
        // get matches from adapter and set it to tourney
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
        getMenuInflater().inflate(R.menu.menu_round, menu);
        mMenu = menu;
        mPresenter.setActionRoundView(mMenu.findItem(R.id.action_start_round), mMenu.findItem(R.id.action_end_round));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_start_round:
                mPresenter.startRoundAction(item);
                break;
            case R.id.action_end_round:
                mPresenter.endRoundAction(item);
                break;
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        mPresenter.setActionRoundView(mMenu.findItem(R.id.action_start_round), mMenu.findItem(R.id.action_end_round));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setViewComponent() {
        setRecyclerView();
        setToolbar();
    }

    @Override
    public void showReportResultMessage(String msg) {
        ToastUtil.showShortMessage(msg, this);
    }


    @Override
    public void showRoundMessage(String msg) {
        ToastUtil.showShortMessage(msg, this);
    }

    @Override
    public void setEnabled(View view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @Override
    public void setMenuItemOpacity(MenuItem item, int alpha) {
        Drawable icon = item.getIcon();
        icon.setAlpha(alpha);
    }

    @Override
    public void inflateByePlayerView(String name) {
        TextView view = (TextView) findViewById(R.id.text_view_bye_player_name);
        view.setText(name);
        view.setVisibility(View.VISIBLE);
        view = (TextView) findViewById(R.id.text_view_bye_player_title);
        view.setVisibility(View.VISIBLE);
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerBoldItemDecoration(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MatchesAdapter(this, new ArrayList<Match>());
        mAdapter.setOnItemClickListener(onViewClickListener);
        mRecyclerView.setAdapter(new SlideInBottomAnimationAdapter(mAdapter));
    }

    private void setToolbar() {
        String title = getString(R.string.title_activity_round_number, mPresenter.getRoundNumber());
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private MatchesAdapter.OnViewClickListener onViewClickListener =
            new MatchesAdapter.OnViewClickListener() {
                @Override
                public void onButtonClicked(View view, Match match, Points resPlayer1, Points resPlayer2) {
                    mPresenter.onMatchClicked(view, match, resPlayer1, resPlayer2);
                }
            };
}
