package com.arles.swissmanager.test.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.activity.NewPlayerActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Checks.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.arles.swissmanager.test.util.AdapterViewMatcher.withAdaptedData;
import static com.arles.swissmanager.test.util.StringMatcher.withItemContent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.is;

/**
 * Created by Admin on 09.07.2015.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NewPlayerActivityTest {

    @Rule
    public ActivityTestRule<NewPlayerActivity> mActivityRule = new ActivityTestRule<>(NewPlayerActivity.class);

    @Test
    public void testAddItemToRecyclerView() {

        onView(withId(R.id.edit_text_new_player_name)).perform(typeText("Player 1"));
        onView(withId(R.id.button_add_item)).perform(click());
        onView(withId(R.id.edit_text_new_player_name)).check(matches(withText(is(""))));



    }



}
