package com.arles.swissmanager.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.activity.NewPlayerActivity;

/**
 * Created by Admin on 02.06.2015.
 */
public class NewPlayerActivityTest extends ActivityInstrumentationTestCase2<NewPlayerActivity> {

    private NewPlayerActivity mFirstTestActivity;
    private EditText mFirstTestText;

    public NewPlayerActivityTest() {
        super(NewPlayerActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mFirstTestActivity = getActivity();
        mFirstTestText = (EditText) mFirstTestActivity.findViewById(R.id.editText_new_player_name);

    }

    public void testPreconditions() {
        assertNotNull("mFirstTestActivity is null", mFirstTestActivity);
        assertNotNull("mFirstTestText is null", mFirstTestText);
    }

}
