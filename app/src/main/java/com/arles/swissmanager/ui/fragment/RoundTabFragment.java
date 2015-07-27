package com.arles.swissmanager.ui.fragment;

/**
 * Created by Admin on 06.07.2015.
 */
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arles.swissmanager.R;
import com.arles.swissmanager.algorithm.Round;
import com.arles.swissmanager.ui.adapter.RoundsAdapter;
import com.arles.swissmanager.ui.presenter.RoundTabPresenter;
import com.arles.swissmanager.utils.DividerItemDecoration;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.InjectView;

public class RoundTabFragment extends BaseFragment implements RoundTabPresenter.IView, FragmentSwitchListener {

    @InjectView(R.id.recycler_view_rounds)
    RecyclerView mRecyclerView;
    @Inject
    RoundTabPresenter mPresenter;
    private RoundsAdapter mAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        mPresenter.initializeViewComponent();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.tab_round;
    }

    @Override
    public void setViewComponent() {
        setRecyclerView();
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RoundsAdapter(new ArrayList<Round>());
        mAdapter.setData(mPresenter.getRoundList());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onVisible() {
        mAdapter.setData(mPresenter.getRoundList());
    }
}
