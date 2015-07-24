package com.arles.swissmanager.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arles.swissmanager.R;
import com.arles.swissmanager.algorithm.Match;
import com.arles.swissmanager.algorithm.Round;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * RoundsAdapter manages Round data model and adapts it to RecyclerView, which is in RoundTabFragment.
 * Created by Admin on 24.07.2015.
 */
public class RoundsAdapter extends RecyclerView.Adapter<RoundsAdapter.ViewHolder> {

    private List<Round> mList;

    public RoundsAdapter() {
    }

    public void setData(List<Round> list) {
        mList = list;
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
        holder.mRoundName.setText("Round ".concat(mList.get(position).toString()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.text_view_round_name)
        TextView mRoundName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
