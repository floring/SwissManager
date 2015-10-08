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
