package com.arles.swissmanager.utils;

import android.widget.EditText;

/**
 * Created by Admin on 08.07.2015.
 */
public class TextUtil {

    public static boolean isValidContent(String text) {
        return (text != null && text.length() > 0);
    }
}
