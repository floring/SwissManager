package com.arles.swissmanager.ui.presenter;

import android.content.Intent;

import com.arles.swissmanager.ui.activity.Navigator;
import com.arles.swissmanager.utils.KeyExtra;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * MainPresenter created to show MainActivity.
 * <p/>
 * Created by Admin on 18.05.2015.
 */
@Singleton
public class MainPresenter extends Presenter {

    private IView view;
    private Navigator mNavigator;

    @Inject
    public MainPresenter(Navigator navigator) {
        mNavigator = navigator;
    }

    public void setView(IView view) {
        this.view = view;
    }

    public void createNew(int requestCode) {
        mNavigator.startNewPlayerActivityForResult(requestCode);
    }

    public void add(Intent data) {
        data.getStringExtra(KeyExtra.NEW_PLAYER_NAME);
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface IView {

    }

}
