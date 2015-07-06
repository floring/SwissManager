package com.arles.swissmanager.ui.presenter;

import javax.inject.Inject;

/**
 * Created by Admin on 02.07.2015.
 */
public class TourneyPresenter extends Presenter {

    private IView mView;

    @Inject
    public TourneyPresenter() {}

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
