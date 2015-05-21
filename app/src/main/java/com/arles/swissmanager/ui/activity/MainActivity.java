package com.arles.swissmanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.fragment.NavigationDrawerFragment;
import com.arles.swissmanager.ui.presenter.MainPresenter;
import com.arles.swissmanager.ui.presenter.UIModule;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements MainPresenter.IView {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.fab_add)
    ImageButton mFAButton;
    @Inject
    MainPresenter mMainPresenter;

    private static final int REQUEST_CODE_CREATE_NEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectViews();

        setUpNavDrawerFragment();
        mMainPresenter.setView(this);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab_add)
    public void faButtonCreateNew() {
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
}
