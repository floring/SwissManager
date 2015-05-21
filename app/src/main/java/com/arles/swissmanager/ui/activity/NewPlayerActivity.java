package com.arles.swissmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.presenter.NewPlayerPresenter;
import com.arles.swissmanager.ui.presenter.UIModule;
import com.arles.swissmanager.utils.KeyExtra;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

public class NewPlayerActivity extends BaseActivity implements NewPlayerPresenter.IView {

    public static final int ACTIVITY_TITLE = R.string.title_activity_new_player;

    @InjectView(R.id.toolbar) Toolbar mToolbar;
    @Inject NewPlayerPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
        injectViews();
        setToolbarView();
        mPresenter.setView(this);
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

    private void setToolbarView() {
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
        CharSequence playerName = ((EditText) findViewById(R.id.editText_new_player_name)).getText();
        mPresenter.bundleData(playerName);
    }

    @Override
    public void sendDataToLaunchActivity(Intent intent) {
        setResult(RESULT_OK, intent);
    }

    @Override
    public void close() {
        finish();
    }
}
