package com.arles.swissmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.arles.swissmanager.R;
import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.algorithm.Match;
import com.arles.swissmanager.ui.adapter.RecyclerViewAdapter;
import com.arles.swissmanager.ui.fragment.NavigationDrawerFragment;
import com.arles.swissmanager.ui.model.Player;
import com.arles.swissmanager.ui.presenter.MainPresenter;
import com.arles.swissmanager.ui.presenter.UIModule;
import com.arles.swissmanager.utils.DividerItemDecoration;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnTouch;


public class MainActivity extends BaseActivity implements MainPresenter.IView, ActionMode.Callback, RecyclerView.OnItemTouchListener, NavigationDrawerFragment.INavigationDrawerListener {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.recycler_view_players)
    RecyclerView mRecyclerView;
    @Inject
    MainPresenter mMainPresenter;

    private RecyclerViewAdapter mAdapter;
    private ActionMode mActionMode;
    private GestureDetector mGestureDetector;
    private static final int REQUEST_CODE_CREATE_NEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectViews();
        mMainPresenter.setView(this);
        mMainPresenter.initializeViewComponent();
    }

    @Override
    protected List<Object> getModules() {
        // TODO: FIX IT!!
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new UIModule());
        return modules;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab_add)
    public void onFaButtonClick() {
        mMainPresenter.createNew(REQUEST_CODE_CREATE_NEW);
    }

    private void setUpNavDrawerFragment() {
        NavigationDrawerFragment navDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navDrawerFragment.setUp(
                findViewById(R.id.fragment_navigation_drawer),
                (DrawerLayout) findViewById(R.id.drawer_layout),
                mToolbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CREATE_NEW) {
            mMainPresenter.add(data);
        }
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnItemTouchListener(this);
        mAdapter = new RecyclerViewAdapter(SwissManagerApplication.getTestData());
        mRecyclerView.setAdapter(mAdapter);
        mGestureDetector = new GestureDetector(this, new RecyclerViewGestureListener());
    }

    @Override
    public void setViewComponent() {
        setToolbar();
        setUpNavDrawerFragment();
        setRecyclerView();
    }

    private void setToolbar() {
        // found without this function toolbar doesn't show action items
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void addRecyclerItem(String itemTitle) {
        mAdapter.addPlayer(itemTitle);
    }

    @Override
    public void removeRecyclerItem() {
        List<Integer> selectedItemPositions = mAdapter.getSelectedItems();
        int currPos;
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            currPos = selectedItemPositions.get(i);
            mAdapter.removePlayer(currPos);
        }
    }

    public void onRecyclerItemTap(View view) {
        int id = mRecyclerView.getChildPosition(view);
        if (mActionMode != null) {
            toggleItem(id);
            return;
        }
        // really tap
    }

    /**
     * Inflate a ContextActionMode menu
     */
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.menu_main_cam, menu);
        findViewById(R.id.fab_add).setVisibility(View.GONE);
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
                mMainPresenter.remove();
                mActionMode.finish();
                return true;
            default:
                return false;
        }
    }

    /**
     * Destroy ActionMode prior to show the normal Toolbar
     */
    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mActionMode = null;
        mAdapter.clearSelection();
        findViewById(R.id.fab_add).setVisibility(View.VISIBLE);
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

    @Override
    public List<Player> getPlayersDataListener() {
        return mAdapter.getPlayers();
    }

    @Override
    public void sendPlayerDataListener(List<Player> list) {
        mAdapter.setPlayers(list);
    }

    private class RecyclerViewGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            onRecyclerItemTap(view);
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (mActionMode == null) {
                mActionMode = startActionMode(MainActivity.this);
                toggleItem(mRecyclerView.getChildPosition(view));
                super.onLongPress(e);
            }
        }
    }
}
