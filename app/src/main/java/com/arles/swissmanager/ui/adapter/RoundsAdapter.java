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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arles.swissmanager.R;
import com.arles.swissmanager.tournament.Round;
import com.arles.swissmanager.utils.CollectionValidator;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * RoundsAdapter manages Round data model and adapts it to RecyclerView, which is in RoundTabFragment.
 * Created by Admin on 24.07.2015.
 */
public class RoundsAdapter extends RecyclerView.Adapter<RoundsAdapter.ViewHolder> {

    private List<Round> mList;
    private OnItemClickListener mListener;

    public RoundsAdapter(List<Round> list) {
        mList = list;
    }

    public void setData(List<Round> list) {
        CollectionValidator.validateOnNull(list);
        mList = list;
        notifyDataSetChanged();
    }

    public List<Round> getData() {
        return mList;
    }

    @Override
    public RoundsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_round_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RoundsAdapter.ViewHolder holder, int position) {
        Round round = mList.get(position);
        int number = round.getNumber();
        holder.mRoundName.setText("Round ".concat(Integer.toString(number)));
        holder.mRoundState.setText(round.getState().toString().toLowerCase());
    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.text_view_round_name)
        TextView mRoundName;
        @InjectView(R.id.text_view_round_state)
        TextView mRoundState;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        @OnClick(R.id.round_layout)
        public void itemClick(View view) {
            if (mListener != null) {
                mListener.onItemClicked(view, getPosition());
            }
        }
    }
}
