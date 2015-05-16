package com.arles.swissmanager.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.fragment.NavigationDrawerFragment;
import com.arles.swissmanager.ui.presenter.UIModule;

import java.util.LinkedList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private NavigationDrawerFragment mNavDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeNavigationDrawerFragment();
        initializeToolbar();
        setUpNavDrawerFragment();
    }

    @Override
    protected List<Object> getModules() {
        // TODO:
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new UIModule());
        return modules;
    }


    private void initializeNavigationDrawerFragment() {
        mNavDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
    }

    private void initializeToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpNavDrawerFragment() {
        mNavDrawerFragment.setUp(
                findViewById(R.id.fragment_navigation_drawer),
                (DrawerLayout) findViewById(R.id.drawer_layout),
                mToolbar);
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
}
