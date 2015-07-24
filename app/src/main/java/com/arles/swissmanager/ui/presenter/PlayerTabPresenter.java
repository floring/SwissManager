package com.arles.swissmanager.ui.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.arles.swissmanager.utils.KeyExtra;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * PlayerTabPresenter created to show PlayerTabFragment.
 * Created by Admin on 06.07.2015.
 */
public class PlayerTabPresenter extends Presenter {

    private IView mView;

    @Inject
    public PlayerTabPresenter() {

    }

    public void setView(IView view) {
        mView = view;
    }

    @Override
    public void initializeViewComponent() {
        mView.setViewComponent();
    }

    public void launchActivity(int requestCode) {

    }

    public void parseActivityResult(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            ArrayList<String> namesList = bundle.getStringArrayList(KeyExtra.KEY_BUFFER_PLAYERS_NAME_LIST);
            if(namesList != null) {
                mView.addToAdapter(namesList);
            }
        }
    }

    public interface IView {
        void setViewComponent();
        void addToAdapter(ArrayList<String> namesList);
    }
}
