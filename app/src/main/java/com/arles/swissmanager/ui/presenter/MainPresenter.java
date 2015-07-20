package com.arles.swissmanager.ui.presenter;

import android.content.Intent;

import com.arles.swissmanager.ui.activity.NavigatorActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * MainPresenter created to show MainActivity.
 * <p/>
 * Created by Admin on 18.05.2015.
 */
@Singleton
public class MainPresenter extends Presenter {

    private IView mView;
    private NavigatorActivity mNavigator;

    @Inject
    public MainPresenter(NavigatorActivity navigator) {
        mNavigator = navigator;
    }

    public void setView(IView view) {
        mView = view;
    }

    public void createNew(int requestCode) {
        mNavigator.startNewPlayerActivityForResult(requestCode);
    }

    public void add(Intent data) {
//        String str = data.getStringExtra(KeyExtra.NEW_PLAYER_NAME);
//        mView.addRecyclerItem(str);
    }

    public void remove() {
        mView.removeRecyclerItem();
    }

    @Override
    public void initializeViewComponent() {
        mView.setViewComponent();
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface IView {
        void setViewComponent();
        void addRecyclerItem(String itemTitle);
        void removeRecyclerItem();
    }

}
