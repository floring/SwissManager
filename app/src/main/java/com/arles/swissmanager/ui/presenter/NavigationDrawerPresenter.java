package com.arles.swissmanager.ui.presenter;

import com.arles.swissmanager.algorithm.Match;
import com.arles.swissmanager.algorithm.Sorter;
import com.arles.swissmanager.ui.model.Player;

import java.util.List;

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
    public NavigationDrawerPresenter() {
    }

    public void setView(INavigationView view) {
        mView = view;
    }

    @Override
    public void initializeViewComponent() {
    }

    public void sortByPrestige() {
        Sorter sorter = initializeSorter();
        mView.sendDataToActivity(sorter.sort());
    }

    public void makePlayerPairs() {
        Sorter sorter = initializeSorter();
        mView.sendDataToActivity(sorter.doPairsBySwiss());
    }

    private Sorter initializeSorter() {
        List<Player> list = mView.getDataFromActivity();
        return new Sorter(list);
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface INavigationView {
        List<Player> getDataFromActivity();
        void sendDataToActivity(List<Player> list);
    }
}
