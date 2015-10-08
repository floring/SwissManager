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

import android.content.Context;
import android.widget.Toast;

/**
 * ToastUtil contains utility methods related with the Toast class.
 * Created by Admin on 22.07.2015.
 */
public class ToastUtil {

    private ToastUtil() {}

    public static void showError(final String message, final Context context) {
        getToast(message, context).show();
    }

    public static void showShortMessage(String message, Context context) {
        getToast(message, context, Toast.LENGTH_SHORT).show();
    }

    private static Toast getToast(String message, Context context) {
        return getToast(message, context, Toast.LENGTH_LONG);
    }

    private static Toast getToast(String message, Context context, int length) {
        return Toast.makeText(context, message, length);
    }
}
