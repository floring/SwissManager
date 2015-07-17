package com.arles.swissmanager.test.util;

/**
 * This class contains method to sleep the thread on specified time.
 * Created by Admin on 17.07.2015.
 */
public class Sleep {

    private static final int SLEEP_TIME = 5000;

    public static void sleepThread() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (Exception e) {
        }
    }
}
