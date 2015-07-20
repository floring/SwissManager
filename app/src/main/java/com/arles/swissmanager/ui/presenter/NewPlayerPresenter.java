package com.arles.swissmanager.ui.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.arles.swissmanager.utils.KeyExtra;
import com.arles.swissmanager.utils.TextUtil;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Admin on 20.05.2015.
 */
public class NewPlayerPresenter extends Presenter {

    private IView mView;

    @Inject
    public NewPlayerPresenter() {
    }

    public void setView(IView view) {
        mView = view;
    }

    public void add(String text) {
        if (TextUtil.isValidContent(text)) {
            mView.addToBufferList(text);
            mView.clearInputs();
        }
    }

    public void bundleData(ArrayList<String> list) {
        if(list.size() > 0) {
            Bundle bundle = new Bundle(list.size());
            bundle.putStringArrayList(KeyExtra.KEY_BUFFER_PLAYERS_NAME_LIST, list);
            Intent intent = new Intent().putExtras(bundle);
            mView.sendDataToLaunchActivity(intent);
        }
        mView.close();
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

        void sendDataToLaunchActivity(Intent intent);

        void close();

        void addToBufferList(String str);

        void clearInputs();
    }
}
