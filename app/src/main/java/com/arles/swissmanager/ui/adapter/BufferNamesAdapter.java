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

package com.arles.swissmanager.ui.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.arles.swissmanager.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * RecyclerViewBufferAdapter manages names of new players data and adapts it to RecyclerView, which is in NewPlayerActivity.
 * This recycler view contains buffer list of new players names which have not added to Players List yet.
 * Created by Admin on 08.07.2015.
 */
public class BufferNamesAdapter extends RecyclerView.Adapter<BufferNamesAdapter.ViewHolder> {

    private final int NOTIFY_DELAY = 500;

    private ArrayList<String> mDataList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_buffer_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String data = mDataList.get(position);
        holder.mTextItem.setText(data);
        holder.mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });
    }

    public ArrayList<String> getDataList() {
        return mDataList;
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void addItem(final String item) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDataList.add(item);
                // position == position of last item
                notifyItemInserted(mDataList.size() - 1);
            }
        }, NOTIFY_DELAY);
    }

    public void removeItem(final int position) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mDataList.size());
            }
        }, NOTIFY_DELAY);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.text_view_buffer_player_name)
        TextView mTextItem;
        @InjectView(R.id.button_delete_buffer_player)
        ImageButton mButtonDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
