package com.arles.swissmanager.ui.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arles.swissmanager.R;
import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.ui.adapter.RecyclerViewMatchesAdapter;
import com.arles.swissmanager.ui.presenter.RoundPresenter;
import com.arles.swissmanager.ui.presenter.UIModule;
import com.arles.swissmanager.utils.DividerBoldItemDecoration;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import jp.wasabeef.recyclerview.animators.adapters.SlideInBottomAnimationAdapter;

public class RoundActivity extends BaseActivity implements RoundPresenter.IView{

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.recycler_view_matches)
    RecyclerView mRecyclerView;
    @Inject
    RoundPresenter mPresenter;
    private RecyclerViewMatchesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
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
        getMenuInflater().inflate(R.menu.menu_round, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setViewComponent() {
        setRecyclerView();
        setToolbar();
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerBoldItemDecoration(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerViewMatchesAdapter(this, mPresenter.getMatchesData());
        mRecyclerView.setAdapter(new SlideInBottomAnimationAdapter(mAdapter));
    }

    private void setToolbar() {
        String title = getString(R.string.title_activity_round_number, mPresenter.getRoundNumber());
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
