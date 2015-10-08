/**
 * Copyright (C) 2015 Arles
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arles.swissmanager.ui.presenter;

import android.content.Intent;

import com.arles.swissmanager.ui.activity.Navigator;

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
    private Navigator mNavigator;

    @Inject
    public MainPresenter(Navigator navigator) {
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
