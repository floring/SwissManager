package com.arles.swissmanager.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.arles.swissmanager.R;
import com.arles.swissmanager.tournament.Player;
import com.arles.swissmanager.ui.activity.NewPlayerActivity;
import com.arles.swissmanager.ui.adapter.PlayersAdapter;
import com.arles.swissmanager.ui.presenter.PlayerTabPresenter;
import com.arles.swissmanager.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Admin on 06.07.2015.
 */
public class PlayerTabFragment extends BaseFragment implements PlayerTabPresenter.IView, FragmentParentClickListener, ActionMode.Callback, RecyclerView.OnItemTouchListener {

    private static final int REQUEST_CODE_START_ACTIVITY = 16;

    @InjectView(R.id.recycler_view_players)
    RecyclerView mRecyclerView;
    @InjectView(R.id.fab_add)
    ImageButton mFloatingButton;
    @Inject
    PlayerTabPresenter mPresenter;

    private PlayersAdapter mAdapter;
    private ActionMode mActionMode;
    private GestureDetector mGestureDetector;

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

    @Override
    public void removeRecyclerItem() {
        List<Integer> selectedItemPositions = mAdapter.getSelectedItems();
        int currPos;
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            currPos = selectedItemPositions.get(i);
            //mAdapter.removePlayer(currPos);
        }
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
        if(requestCode == REQUEST_CODE_START_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK) {
                mPresenter.parseActivityResult(data);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.setData(mPresenter.getPlayerList());
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.setPlayerList(mAdapter.getPlayers());
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnItemTouchListener(this);
        mAdapter = new PlayersAdapter(new ArrayList<Player>());
        mAdapter.setData(mPresenter.getPlayerList());
        mRecyclerView.setAdapter(mAdapter);
        mGestureDetector = new GestureDetector(getActivity(), new RecyclerViewGestureListener());
    }

    @Override
    public void onRefresh() {
        mAdapter.setData(mPresenter.getPlayerList());
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.menu_main_action_mode, menu);
        mFloatingButton.setVisibility(View.GONE);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_delete:
                mPresenter.remove();
                mActionMode.finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mActionMode = null;
        mAdapter.clearSelection();
        mFloatingButton.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    private void toggleItem(int id) {
        mAdapter.toggleSelection(id);
        String title = getString(R.string.selected_count, mAdapter.getSelectedItemCount());
        mActionMode.setTitle(title);
    }

    public void onRecyclerItemTap(View view, int position) {
        if (mActionMode != null) {
            toggleItem(position);
            return;
        } else {
            mPresenter.onPlayerItemClick(position);
        }
        // really tap
    }


    private class RecyclerViewGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            int position = mRecyclerView.getChildPosition(view);
            onRecyclerItemTap(view, position);
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (mActionMode == null) {
                mActionMode = getActivity().startActionMode(PlayerTabFragment.this);
                toggleItem(mRecyclerView.getChildPosition(view));
                super.onLongPress(e);
            }
        }
    }
}
