package com.arles.swissmanager.test.util;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Admin on 10.07.2015.
 */
public class RecyclerViewMatcher {

    private final int mRecyclerViewId;

    public RecyclerViewMatcher(int recyclerViewId) {
        mRecyclerViewId = recyclerViewId;
    }

    public Matcher<View> atPosition(final int position) {
        return atPositionOnView(position, -1);
    }

    public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

        return new TypeSafeMatcher<View>() {
            Resources resources = null;
            View childView;

            public void describeTo(Description description) {
                String idDescription = Integer.toString(mRecyclerViewId);
                if (this.resources != null) {
                    try {
                        idDescription = this.resources.getResourceName(mRecyclerViewId);
                    } catch (Resources.NotFoundException var4) {
                        idDescription = String.format("%s (resource name not found)",
                                new Object[]{Integer.valueOf
                                        (mRecyclerViewId)});
                    }
                }
                description.appendText("with id: " + idDescription);
            }

            public boolean matchesSafely(View view) {
                this.resources = view.getResources();
                if (childView == null) {
                    RecyclerView recyclerView =
                            (RecyclerView) view.getRootView().findViewById(mRecyclerViewId);
                    if (recyclerView != null && recyclerView.getId() == mRecyclerViewId) {
                        childView = recyclerView.getChildAt(position);
                    } else {
                        return false;
                    }
                }
                if (targetViewId == -1) {
                    return view == childView;
                } else {
                    View targetView = childView.findViewById(targetViewId);
                    return view == targetView;
                }
            }
        };
    }
}
