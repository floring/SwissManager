package com.arles.swissmanager.test.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.arles.swissmanager.ui.activity.RoundActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by Admin on 17.07.2015.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RoundActivityTest {

    @Rule
    public ActivityTestRule<RoundActivity> mActivityRule = new ActivityTestRule<>(RoundActivity.class);



}

