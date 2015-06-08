package com.arles.swissmanager.test;

import android.test.InstrumentationTestCase;

/**
 * Created by Admin on 02.06.2015.
 */
public class ExampleTest extends InstrumentationTestCase {

    public void test() throws Exception {
        final int expected = 1;
        final int reality = 5;
        assertEquals(expected, reality);
    }
}
