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

package com.arles.swissmanager.test.util;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.equalTo;


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
