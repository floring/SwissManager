package com.arles.swissmanager.ui.presenter;

import android.content.Intent;
import android.text.TextUtils;

import com.arles.swissmanager.utils.KeyExtra;

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

    public void bundleData(CharSequence name) {
        if (!TextUtils.isEmpty(name)) {
            Intent intent = new Intent().putExtra(KeyExtra.NEW_PLAYER_NAME, name.toString());
            mView.sendDataToLaunchActivity(intent);
        }
        mView.close();
    }

    @Override
    public void initializeViewComponent() {
        // Empty
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface IView {
        public void sendDataToLaunchActivity(Intent intent);

        public void close();
    }
}
