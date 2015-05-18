package com.arles.swissmanager.ui.presenter;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arles.swissmanager.ui.fragment.NavigationDrawerFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Presenter created to show a Navigation Drawer Panel.
 * <p/>
 * This is a sample of Model View Presenter pattern.
 * <p/>
 * Created by Admin on 07.05.2015.
 */
@Singleton
public class NavigationDrawerPresenter extends Presenter {

    private INavigationView mView;

    @Inject
    public NavigationDrawerPresenter() { }

    @Override public void initialize() { }

    @Override public void resume() { }

    @Override public void pause() { }

    public void setView(INavigationView view) {
        mView = view;
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface INavigationView {

    }
}
