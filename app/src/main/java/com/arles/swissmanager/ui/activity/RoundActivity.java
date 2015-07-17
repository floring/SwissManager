package com.arles.swissmanager.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.arles.swissmanager.R;
import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.algorithm.Match;
import com.arles.swissmanager.ui.adapter.RecyclerViewAdapter;
import com.arles.swissmanager.ui.adapter.RecyclerViewMatchesAdapter;
import com.arles.swissmanager.ui.model.Player;
import com.arles.swissmanager.ui.presenter.RoundActivityPresenter;
import com.arles.swissmanager.ui.presenter.UIModule;
import com.arles.swissmanager.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by Admin on 16.07.2015.
 */
public class RoundActivity extends BaseActivity implements RoundActivityPresenter.IView{

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.recycler_view_matches)
    RecyclerView mRecyclerView;
    @Inject
    RoundActivityPresenter mPresenter;
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
    public void setViewComponent() {
        setRecyclerView();
        setToolbar();
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setHasFixedSize(true);

        ArrayList<Match> list = new ArrayList<>();
        list.add(new Match(new Player("John"), new Player("Alex")));
        list.add(new Match(new Player("David"), new Player("Vadim")));
        mAdapter = new RecyclerViewMatchesAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
