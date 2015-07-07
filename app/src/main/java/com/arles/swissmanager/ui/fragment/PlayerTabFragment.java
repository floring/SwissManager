package com.arles.swissmanager.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arles.swissmanager.R;
import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.ui.adapter.RecyclerViewAdapter;
import com.arles.swissmanager.ui.presenter.PlayerTabPresenter;
import com.arles.swissmanager.utils.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.InjectView;


/**
 * Created by Admin on 06.07.2015.
 */
public class PlayerTabFragment extends BaseFragment implements PlayerTabPresenter.IView {

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

    private void setRecyclerView() {
        Context context = getActivity().getApplicationContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerViewAdapter(SwissManagerApplication.getTestData());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setViewComponent() {
        setRecyclerView();
    }
}
