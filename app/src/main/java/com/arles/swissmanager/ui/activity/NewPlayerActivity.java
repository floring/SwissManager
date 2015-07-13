package com.arles.swissmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.adapter.RecyclerViewBufferAdapter;
import com.arles.swissmanager.ui.presenter.NewPlayerPresenter;
import com.arles.swissmanager.ui.presenter.UIModule;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

public class NewPlayerActivity extends BaseActivity implements NewPlayerPresenter.IView {

    public static final int ACTIVITY_TITLE = R.string.title_activity_new_player;
    public static final int ANIMATION_DURATION = 2000;

    @InjectView(R.id.toolbar) Toolbar mToolbar;
    @InjectView(R.id.edit_text_new_player_name) EditText mInput;
    @InjectView(R.id.recycler_view_buffer_items)
    RecyclerView mRecyclerView;
    @Inject NewPlayerPresenter mPresenter;

    private RecyclerViewBufferAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
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
        getMenuInflater().inflate(R.menu.menu_new_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        mToolbar.setTitle(ACTIVITY_TITLE);
        mToolbar.setNavigationIcon(R.drawable.ic_done);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavigationIconClick();
            }
        });
    }

    private void onNavigationIconClick() {
        CharSequence playerName = ((EditText) findViewById(R.id.edit_text_new_player_name)).getText();
        mPresenter.bundleData(playerName);

    }

    @Override
    public void setViewComponent() {
        setToolbar();
        setRecyclerView();
    }

    private void setRecyclerView() {
        mAdapter = new RecyclerViewBufferAdapter();
        ScaleInBottomAnimator animator = new ScaleInBottomAnimator();
        animator.setAddDuration(ANIMATION_DURATION);
        animator.setRemoveDuration(ANIMATION_DURATION);
        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.button_add_item)
    public void onAddItemClick() {
        mPresenter.add(mInput.getText().toString());
    }

    @Override
    public void sendDataToLaunchActivity(Intent intent) {
        setResult(RESULT_OK, intent);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void addToBufferList(String str) {
        mAdapter.addItem(str);
    }

    @Override
    public void clearInputs() {
        mInput.setText("");
    }
}
