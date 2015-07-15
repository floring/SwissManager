package com.arles.swissmanager.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arles.swissmanager.R;
import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.ui.activity.NewPlayerActivity;
import com.arles.swissmanager.ui.adapter.RecyclerViewAdapter;
import com.arles.swissmanager.ui.presenter.PlayerTabPresenter;
import com.arles.swissmanager.utils.DividerItemDecoration;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Admin on 06.07.2015.
 */
public class PlayerTabFragment extends BaseFragment implements PlayerTabPresenter.IView {

    private static final int REQUEST_CODE_START_ACTIVITY = 1;

    @InjectView(R.id.recycler_view_players)
    RecyclerView mRecyclerView;
    @Inject
    PlayerTabPresenter mPresenter;

    private RecyclerViewAdapter mAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        mPresenter.initializeViewComponent();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.tab_player;
    }

    @Override
    public void setViewComponent() {
        setRecyclerView();
    }

    @Override
    public void addToAdapter(ArrayList<String> namesList) {
        mAdapter.addNewPlayers(namesList);
    }

    @OnClick(R.id.fab_add)
    public void onFloatingButtonClick() {
        //mPresenter.launchActivity(REQUEST_CODE_START_ACTIVITY);
        Intent intent = new Intent(getActivity().getApplicationContext(), NewPlayerActivity.class);
        startActivityForResult(intent, REQUEST_CODE_START_ACTIVITY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_START_ACTIVITY) {
            mPresenter.parseActivityResult(data);
        }
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerViewAdapter(SwissManagerApplication.getTestData());
        mRecyclerView.setAdapter(mAdapter);
    }
}
