package com.arles.swissmanager.test.util;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


/**
 * Created by Admin on 09.07.2015.
 */
public class StringMatcher {

    private StringMatcher() {}

    public static Matcher<String> withItemContent(String expectedText) {
        // use preconditions to fail fast when a test is creating an invalid matcher
        checkArgument(!(expectedText.equals(null)));
        return withItemContent(equalTo(expectedText));
    }

    public static Matcher<String> withItemContent(final Matcher<String> matcherText) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        checkNotNull(matcherText);
        return new TypeSafeMatcher<String>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("expected text: " + matcherText);
            }

            @Override
            public boolean matchesSafely(String item) {
                // return matcherText.equals(item);
                return matcherText.matches(item);
            }
        };
    }
}
