/*
 * Copyright (C) 2015 Arles. All rights reserved.
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

package com.arles.swissmanager.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewStub;
import android.widget.TextView;

import com.arles.swissmanager.R;
import com.arles.swissmanager.tournament.Player;
import com.arles.swissmanager.ui.adapter.RivalsAdapter;
import com.arles.swissmanager.ui.presenter.PlayerDataPresenter;
import com.arles.swissmanager.ui.presenter.UIModule;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.InjectView;
import jp.wasabeef.recyclerview.animators.adapters.SlideInBottomAnimationAdapter;

public class PlayerDataActivity extends BaseActivity implements PlayerDataPresenter.IView {

    @InjectView(R.id.text_view_player_data_name)
    TextView mNameView;
    @InjectView(R.id.text_view_player_data_prestige)
    TextView mPrestigeView;
    @InjectView(R.id.recycler_view_player_data_rivals)
    RecyclerView mRecyclerView;
    @Inject
    PlayerDataPresenter mPresenter;
    private RivalsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_data);
        injectViews();

        mPresenter.getPassedExtrasFrom(getIntent());
        mPresenter.setView(this);
        mPresenter.initializeViewComponent();
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new UIModule());
        return modules;
    }

    @Override
    public void setViewComponent() {
        setRecyclerView();
        setViewFieldValue();
    }

    private void setViewFieldValue() {
        mNameView.setText(mPresenter.getPlayerName());
        mPrestigeView.setText(mPresenter.getPlayerPrestige());
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RivalsAdapter(new HashSet<Player>());

        Set<Player> l = mPresenter.getPlayerRivalsCollection();
        if(l.size() == 0) {
            ((ViewStub) findViewById(R.id.stub_import)).inflate();
        } else {
            mAdapter.setDataList(l);
        }
        mRecyclerView.setAdapter(new SlideInBottomAnimationAdapter(mAdapter));
    }
}
