package com.arles.swissmanager.utils;

/**
 * Created by Admin on 08.07.2015.
 */
public class TextUtil {

    public static final String EMPTY_STRING = "";

    public static boolean isValidContent(String text) {
        return (text != null && !text.trim().isEmpty());
    }
}
