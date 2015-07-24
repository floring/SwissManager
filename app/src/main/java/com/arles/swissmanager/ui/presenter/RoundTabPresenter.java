package com.arles.swissmanager.ui.presenter;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * RoundTabPresenter created to show RoundTabFragment.
 * Created by Admin on 24.07.2015.
 */
public class RoundTabPresenter extends Presenter {

    private IView mView;

    @Inject
    public RoundTabPresenter() { }

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
