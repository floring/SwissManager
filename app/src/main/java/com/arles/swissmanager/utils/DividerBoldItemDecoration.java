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
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arles.swissmanager.R;

/**
 * DividerBoldItemDecoration draws bold dividers every two recyclerview items.
 * Created by Admin on 17.07.2015.
 */
public class DividerBoldItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private Drawable mDividerBold;

    public DividerBoldItemDecoration(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        mDividerBold = context.getResources().getDrawable(R.drawable.line_divider_bold);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            if(i % 2 == 0) {
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            } else {
                int bottom = top + mDividerBold.getIntrinsicHeight();

                mDividerBold.setBounds(left, top, right, bottom);
                mDividerBold.draw(c);
            }
        }

    }
}
