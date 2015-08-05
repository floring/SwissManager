package com.arles.swissmanager.ui.presenter;

import com.arles.swissmanager.tournament.MatchesCreator;
import com.arles.swissmanager.tournament.Player;
import com.arles.swissmanager.ui.fragment.NavigationDrawerFragment;

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

    private NavigationDrawerFragment mView;

    @Inject
    public NavigationDrawerPresenter() {
    }

    public void setView(NavigationDrawerFragment view) {
        mView = view;
    }

    @Override
    public void initializeViewComponent() {
    }

    public void sortByPrestige() {
        MatchesCreator sorter = initializeSorter();
       // mView.sendDataToActivity(sorter.sort());
    }

    public void makePlayerPairs() {
        MatchesCreator sorter = initializeSorter();
       // mView.sendDataToActivity(sorter.doPairsBySwiss());
    }

    private MatchesCreator initializeSorter() {
        //List<Player> list = mView.getDataFromActivity();
        //return new MatchesCreator(list);
        return null;
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface INavigationView {
        List<Player> getDataFromActivity();
        void sendDataToActivity(List<Player> list);
    }
}
