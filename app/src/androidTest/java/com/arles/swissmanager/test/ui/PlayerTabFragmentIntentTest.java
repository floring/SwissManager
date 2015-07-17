package com.arles.swissmanager.test.ui;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.arles.swissmanager.R;
import com.arles.swissmanager.test.util.Sleep;
import com.arles.swissmanager.ui.activity.NewPlayerActivity;
import com.arles.swissmanager.ui.activity.TourneyActivity;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by Admin on 15.07.2015.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlayerTabFragmentIntentTest {

    private final String FULL_CLASS_NAME = "com.arles.swissmanager.ui.activity.NewPlayerActivity";
    private final String SHORT_CLASS_NAME = ".ui.activity.NewPlayerActivity";
    private final ArrayList<String> PLAYERS_NAMES = new ArrayList<String>() {{
            add("New player is added");
        }};

    @Rule
    public IntentsTestRule<TourneyActivity> mActivityRule = new IntentsTestRule<>(TourneyActivity.class);

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new ActivityResult(Activity.RESULT_OK, null));
    }

    /**
     * Tests that floating button launches activity, e.g. sends correct intent
     */
    @Test
    public void testFloatingButtonClick_LaunchActivity() {
        onView(withId(R.id.fab_add)).perform(click());
        //intended(hasComponent(FULL_CLASS_NAME)); or
        intended(hasComponent(hasShortClassName(SHORT_CLASS_NAME)));
    }

    /**
     * Tests that retrieved data from launched Activity is added to recycler view.
     */
    @Test
    public void testRetrievedData_AddToRecyclerView() {
        // check that recycler view have not contained yet item with given name
        onView(withId(R.id.recycler_view_players)).check(matches(hasDescendant(not(withText(PLAYERS_NAMES.get(0))))));
        // Stub all Intents to NewPlayerActivity to return PLAYERS_NAMES
        intending(hasComponent(hasShortClassName(SHORT_CLASS_NAME))).respondWith(
                new ActivityResult(Activity.RESULT_OK, NewPlayerActivity.createResultData(PLAYERS_NAMES)));

        onView(withId(R.id.fab_add)).perform(click());

        Sleep.sleepThread();
        //check that recycler view now contains player name
        onView(withId(R.id.recycler_view_players)).check(matches(hasDescendant((withText(PLAYERS_NAMES.get(0))))));
    }
}
