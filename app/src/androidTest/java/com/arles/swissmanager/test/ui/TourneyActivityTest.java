package com.arles.swissmanager.test.ui;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.activity.RoundActivity;
import com.arles.swissmanager.ui.activity.TourneyActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Admin on 07.07.2015.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TourneyActivityTest {

    private final String SHORT_CLASS_NAME = ".ui.activity.RoundActivity";

    @Rule
    public IntentsTestRule<TourneyActivity> mActivityRule = new IntentsTestRule<>(TourneyActivity.class);

    /**
     * Tests that action bar item has required actions options
     */
    @Test
    public void testActionBarItemClick() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.action_start_round)).check(matches(isDisplayed()));
        onView(withText(R.string.action_end_round)).check(matches(isDisplayed()));
        onView(withText(R.string.action_settings)).check(matches(isDisplayed()));
    }

    /**
     * Tests that correct intent is sent by clicking on action bar option "Start round"
     */
    @Test
    public void testActionBarOptions_Click_SendIntent() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.action_start_round)).perform(click());
        intended(hasComponent(hasShortClassName(SHORT_CLASS_NAME)));
    }


}
