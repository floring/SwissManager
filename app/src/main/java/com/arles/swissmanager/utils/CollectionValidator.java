package com.arles.swissmanager.utils;

import java.util.Collection;

/**
 * This class contains methods that validate passed data structure from Java collection class.
 * Created by Admin on 27.07.2015.
 */
public class CollectionValidator {

    public static void validateOnNull(Collection collection) {
        if (collection == null) {
            throw new IllegalArgumentException("The collection cannot be null");
        }
    }

    public static boolean isEmpty(Collection collection) {
        return (collection.size() == 0) ? true : false;
    }
}
