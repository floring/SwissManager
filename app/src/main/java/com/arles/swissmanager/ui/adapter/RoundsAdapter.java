package com.arles.swissmanager.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arles.swissmanager.R;
import com.arles.swissmanager.algorithm.Match;
import com.arles.swissmanager.algorithm.Round;
import com.arles.swissmanager.utils.CollectionValidator;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * RoundsAdapter manages Round data model and adapts it to RecyclerView, which is in RoundTabFragment.
 * Created by Admin on 24.07.2015.
 */
public class RoundsAdapter extends RecyclerView.Adapter<RoundsAdapter.ViewHolder> {

    private List<Round> mList;

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
        int number = mList.get(position).getNumber();
        holder.mRoundName.setText("Round ".concat(Integer.toString(number)));
    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    private void validateUsersCollection(List<Round> roundList) {
        if (roundList == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
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
