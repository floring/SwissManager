package com.arles.swissmanager.ui.activity;

import android.os.Bundle;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.presenter.UIModule;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Admin on 16.07.2015.
 */
public class RoundActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        injectViews();
    }


    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new UIModule());
        return modules;
    }
}
