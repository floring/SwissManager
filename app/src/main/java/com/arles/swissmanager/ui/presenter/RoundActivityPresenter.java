package com.arles.swissmanager.ui.presenter;

import javax.inject.Inject;

/**
 * Created by Admin on 16.07.2015.
 */
public class RoundActivityPresenter extends Presenter {

    private IView mView;

    @Inject
    public RoundActivityPresenter() {
    }

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
