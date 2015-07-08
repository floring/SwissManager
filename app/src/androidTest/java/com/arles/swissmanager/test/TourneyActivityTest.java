package com.arles.swissmanager.test;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.arles.swissmanager.ui.activity.TourneyActivity;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arles.swissmanager.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by Admin on 07.07.2015.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TourneyActivityTest {

    @Rule
    public ActivityTestRule<TourneyActivity> mActivityRule = new ActivityTestRule<>(TourneyActivity.class);

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
     * Tests that a view pager can be swiped in both directions.
     * Tests that trying to swipe beyond the start of a view pager has no effect.
     * Tests that trying to swipe beyond the end of a view pager has no effect.
     */
    @Test
    public void testSwipeLeftRight() {
        onView(withText(R.string.tab_player)).check(matches(isDisplayed()));
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withText(R.string.tab_round)).check(matches(isDisplayed()));
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withText(R.string.tab_round)).check(matches(isDisplayed()));
        onView(withId(R.id.view_pager)).perform(swipeRight());
        onView(withText(R.string.tab_player)).check(matches(isDisplayed()));
    }

    /**
     * Tests that swiping across tab displays correct views
     */
    @Test
    public void testSwipeThroughView() {
        onView(withId(R.id.fab_add)).check(matches(isDisplayed()));
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withId(R.id.fab_add)).check(matches(not(isDisplayed())));
        /*The above approach works if the view is still part of the hierarchy. If it is not, you will get a NoMatchingViewException and you need to use ViewAssertions.doesNotExist*/
    }

}
