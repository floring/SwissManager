package com.arles.swissmanager.ui.presenter;

import javax.inject.Inject;

/**
 * Created by Admin on 06.07.2015.
 */
public class PlayerTabPresenter extends Presenter {

    private IView mView;

    @Inject
    public PlayerTabPresenter() {}

    public void setView(IView view) {
        mView = view;
    }

    @Override
    public void initializeViewComponent() {
        mView.setViewComponent();
    }

    public interface IView {
        void setViewComponent();
    }
}
