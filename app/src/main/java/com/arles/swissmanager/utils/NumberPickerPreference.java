package com.arles.swissmanager.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.arles.swissmanager.R;

/**
 * A {@link android.preference.Preference} that displays a number picker as a dialog.
 */
public class NumberPickerPreference extends DialogPreference {

    private final int DEFAULT_MAX_VALUE = 10;
    private final int DEFAULT_MIN_VALUE = 0;

    private int mMin, mMax, mDefault;

    private String mMaxExternalKey, mMinExternalKey;

    private NumberPicker mNumberPicker;

    public NumberPickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
//        TypedArray dialogType = context.obtainStyledAttributes(attrs,
//                com.android.internal.R.styleable.DialogPreference, 0, 0);
        TypedArray dialogType = context.obtainStyledAttributes(attrs, new int[]{Resources.getSystem().getIdentifier("DialogPreference", "styleable", "android")}, 0, 0);
        TypedArray numberPickerType = context.obtainStyledAttributes(attrs,
                R.styleable.NumberPickerPreference, 0, 0);

        mMaxExternalKey = numberPickerType.getString(R.styleable.NumberPickerPreference_maxExternal);
        mMinExternalKey = numberPickerType.getString(R.styleable.NumberPickerPreference_minExternal);

        mMax = numberPickerType.getInt(R.styleable.NumberPickerPreference_max, DEFAULT_MAX_VALUE);
        mMin = numberPickerType.getInt(R.styleable.NumberPickerPreference_min, DEFAULT_MIN_VALUE);

        //mDefault = dialogType.getInt(com.android.internal.R.styleable.Preference_defaultValue, mMin);
        //mDefault = dialogType.getInt(Resources.getSystem().getIdentifier("Preference_defaultValue", "styleable", "android"), mMin);
        mDefault = numberPickerType.getInt(R.styleable.NumberPickerPreference_default_value, mMin);

        dialogType.recycle();
        numberPickerType.recycle();
    }

    @Override
    protected View onCreateDialogView() {
        int max = mMax;
        int min = mMin;

        // External values
        if (mMaxExternalKey != null) {
            max = getSharedPreferences().getInt(mMaxExternalKey, mMax);
        }
        if (mMinExternalKey != null) {
            min = getSharedPreferences().getInt(mMinExternalKey, mMin);
        }

        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.number_picker_dialog, null);

        mNumberPicker = (NumberPicker) view.findViewById(R.id.number_picker);

        // Initialize state
        mNumberPicker.setMaxValue(max);
        mNumberPicker.setMinValue(min);
        mNumberPicker.setValue(getPersistedInt(mDefault));
        mNumberPicker.setWrapSelectorWheel(false);

        // No keyboard popup
        EditText textInput = (EditText) mNumberPicker.findViewById(Resources.getSystem().getIdentifier("numberpicker_input", "id", "android"));

        //EditText textInput = (EditText) mNumberPicker.findViewById(com.android.internal.R.id.numberpicker_input);
        textInput.setCursorVisible(false);
        textInput.setFocusable(false);
        textInput.setFocusableInTouchMode(false);

        return view;
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            persistInt(mNumberPicker.getValue());
        }
    }
}
