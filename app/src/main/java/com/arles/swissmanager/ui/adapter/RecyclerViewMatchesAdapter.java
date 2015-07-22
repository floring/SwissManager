package com.arles.swissmanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.arles.swissmanager.R;
import com.arles.swissmanager.algorithm.Match;
import com.arles.swissmanager.algorithm.Points;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Admin on 16.07.2015.
 */
public class RecyclerViewMatchesAdapter extends RecyclerView.Adapter<RecyclerViewMatchesAdapter.ViewHolder> {

    private Context mContext;
    private List<Match> mDataList;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewMatchesAdapter(Context context, List<Match> list) {
        mContext = context;
        mDataList = list;
    }

    public void setData(List<Match> list) {
        mDataList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_match_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.player1.setText(mDataList.get(position).getPlayer1().getName());
        holder.player2.setText(mDataList.get(position).getPlayer2().getName());
        ArrayAdapter<Points> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, Points.getPointsNames());
        holder.resultPlayer1.setAdapter(adapter);
        holder.resultPlayer2.setAdapter(adapter);
        holder.btnSendResult.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.text_view_match_player_1)
        TextView player1;
        @InjectView(R.id.text_view_match_player_2)
        TextView player2;
        @InjectView(R.id.spinner_match_result_player_1)
        Spinner resultPlayer1;
        @InjectView(R.id.spinner_match_result_player_2)
        Spinner resultPlayer2;
        @InjectView(R.id.button_send_match_result)
        Button btnSendResult;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        @OnClick(R.id.button_send_match_result)
        public void btnSendResultClick() {
            int rowViewPos = (int) btnSendResult.getTag();
            Match currMatch = mDataList.get(rowViewPos);

            if (RecyclerViewMatchesAdapter.this.onItemClickListener != null) {
                RecyclerViewMatchesAdapter.this.onItemClickListener.onButtonClicked(currMatch,
                        (Points) resultPlayer1.getSelectedItem(),
                        (Points) resultPlayer2.getSelectedItem());
            }
        }
    }

    /**
     * Interface for listening match list events.
     */
    public interface OnItemClickListener {
        void onButtonClicked(Match match, Points resPlayer1, Points resPlayer2);
    }
}
