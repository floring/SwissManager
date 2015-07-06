package com.arles.swissmanager.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arles.swissmanager.R;
import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.ui.adapter.RecyclerViewAdapter;
import com.arles.swissmanager.utils.DividerItemDecoration;

import butterknife.InjectView;
import butterknife.Optional;


/**
 * Created by Admin on 06.07.2015.
 */
public class PlayerTabFragment extends BaseFragment {

     @InjectView(R.id.recycler_view_players) RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
        setRecyclerView();
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
}
