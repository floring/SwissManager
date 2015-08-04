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
import com.arles.swissmanager.ui.adapter.OnItemClickListener;
import com.arles.swissmanager.ui.adapter.RoundsAdapter;
import com.arles.swissmanager.ui.presenter.RoundTabPresenter;
import com.arles.swissmanager.utils.DividerItemDecoration;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.InjectView;

public class RoundTabFragment extends BaseFragment implements RoundTabPresenter.IView, FragmentParentClickListener {

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
    public void onResume() {
        super.onResume();
        mAdapter.setData(mPresenter.getRoundList());
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
        mAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
    }

    private OnItemClickListener onItemClickListener =
            new OnItemClickListener() {
                @Override
                public void onItemClicked(View view, int position) {
                    mPresenter.onRoundItemClick(position);
                }
            };

    @Override
    public void onRefresh() {
        mAdapter.setData(mPresenter.getRoundList());
    }
}
