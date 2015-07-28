package com.arles.swissmanager.test.ui;

import android.support.test.espresso.PerformException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.activity.RoundActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by Admin on 17.07.2015.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RoundActivityTest {

    @Rule
    public ActivityTestRule<RoundActivity> mActivityRule = new ActivityTestRule<>(RoundActivity.class);

    /**
     * Tests thar toolbar is displayed on the screen
     */
    @Test
    public void testToolbarExists() {
        onView(withText(R.string.title_activity_round_number)).check(matches(isDisplayed()));
    }

    /**
     * Tests that clicking on navigate up button shows parent activity
     * @throws PerformException if currently displayed activity is root activity, since pressing back
     *         button would result in application closing.
     */
    @Test
    public void testBackButtonClick_ShowParentActivity() {
        onView(isRoot()).perform(pressBack());
    }

    /**
     * Tests
     */
    @Test
    public void testSendButtonClick_ResultOk() {
        onView(withId(R.id.recycler_view_matches)).perform(actionOnItem(hasDescendant(withId(R.id.button_send_match_result)), click()));


        onView(withText(R.string.result_ok)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testSendButtonClick_ResultInvalid() {
    }





}

