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
import com.arles.swissmanager.tournament.Player;
import com.arles.swissmanager.utils.CollectionValidator;

import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * RivalsAdapter manages Player data model and adapts it to RecyclerView, which is in PlayerDataActivity.
 * Created by Admin on 30.07.2015.
 */
public class RivalsAdapter extends RecyclerView.Adapter<RivalsAdapter.ViewHolder> {

    private Set<Player> mSet;

    public RivalsAdapter(Set<Player> set) {
        mSet = set;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_rival_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object[] k = mSet.toArray();
        //holder.mRivalName.setText(mSet.get(position).getName());
        holder.mRivalName.setText(((Player)k[position]).getName());
    }

    @Override
    public int getItemCount() {
        return (mSet != null) ? mSet.size() : 0;
    }

    public void setDataList(Set<Player> set) {
        CollectionValidator.validateOnNull(set);
            mSet = set;
            notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.text_view_player_data_rival)
        TextView mRivalName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
