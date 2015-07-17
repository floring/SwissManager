package com.arles.swissmanager.test.ui;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.arles.swissmanager.R;
import com.arles.swissmanager.test.util.Sleep;
import com.arles.swissmanager.ui.activity.NewPlayerActivity;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Checks.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.arles.swissmanager.test.util.RecyclerViewMatcher.withRecyclerAdaptedData;
import static com.arles.swissmanager.test.util.StringMatcher.withItemContent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by Admin on 09.07.2015.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NewPlayerActivityTest {

    private final String TEST_PLAYER_NAME = "Player 1";

    @Rule
    public ActivityTestRule<NewPlayerActivity> mActivityRule = new ActivityTestRule<>(NewPlayerActivity.class);

    /**
     * Tests trying to add name by clicking on Add button.
     */
    @Before
    @Test
    public void testAddPlayerButtonClick() {
        onView(withId(R.id.edit_text_new_player_name)).perform(typeText(TEST_PLAYER_NAME));
        onView(withId(R.id.button_add_item)).perform(click());
    }

    /**
     * Tests that player name is added to buffer players list.
     */
    @Test
    public void testAddItemToRecyclerView() {
        onView(withId(R.id.edit_text_new_player_name)).check(matches(withText(is(""))));

        Sleep.sleepThread();
        onView(withId(R.id.recycler_view_buffer_items)).check(matches(withRecyclerAdaptedData(withItemContent(TEST_PLAYER_NAME), R.id.text_view_buffer_player_name)));
    }

    /**
     * Tests that player item is removed from buffer players list by clicking on its Delete button and this view does not exist anymore.
     */
    @Test
    public void testRemoveItemFromRecyclerView() {

        Sleep.sleepThread();
        onView(withId(R.id.recycler_view_buffer_items)).perform(actionOnItem(hasDescendant(withId(R.id.button_delete_buffer_player)), click()));

        Sleep.sleepThread();
        //onView(withRecyclerView(R.id.recycler_view_buffer_items).atPosition(0)).check(doesNotExist());
        onView(withId(R.id.recycler_view_buffer_items)).check(matches(hasDescendant(not(withText(TEST_PLAYER_NAME)))));
    }
}
