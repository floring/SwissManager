/*
 * Copyright (C) 2015 Arles. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arles.swissmanager.ui.fragment;

/**
 * Created by Admin on 06.07.2015.
 */

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.arles.swissmanager.R;
import com.arles.swissmanager.tournament.Round;
import com.arles.swissmanager.ui.adapter.OnItemClickListener;
import com.arles.swissmanager.ui.adapter.RoundsAdapter;
import com.arles.swissmanager.ui.presenter.RoundTabPresenter;
import com.arles.swissmanager.utils.DividerItemDecoration;
import com.arles.swissmanager.utils.ExtendedRecyclerView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.InjectView;

public class RoundTabFragment extends BaseFragment implements RoundTabPresenter.IView, FragmentParentClickListener {

    @InjectView(android.R.id.list)
    ExtendedRecyclerView mRecyclerView;
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
        mRecyclerView.setEmptyView(getView().findViewById(android.R.id.empty));
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
