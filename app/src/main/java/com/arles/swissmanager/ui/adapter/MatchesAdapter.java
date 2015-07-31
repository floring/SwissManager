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
import com.arles.swissmanager.algorithm.MatchResult;
import com.arles.swissmanager.algorithm.Points;
import com.arles.swissmanager.utils.CollectionValidator;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * MatchesAdapter manages Match data model and adapts it to RecyclerView, which is in RoundActivity.
 * Created by Admin on 16.07.2015.
 */
public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    private Context mContext;
    private List<Match> mDataList;
    private OnViewClickListener onItemClickListener;

    public MatchesAdapter(Context context, List<Match> list) {
        mContext = context;
        mDataList = list;
    }

    public void setData(List<Match> list) {
        CollectionValidator.validateOnNull(list);
        mDataList = list;
        notifyDataSetChanged();
    }

    public List<Match> getData() {
        return mDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_match_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Match match = mDataList.get(position);
        holder.player1.setText(match.getPlayer1().getName());
        holder.player2.setText(match.getPlayer2().getName());
        ArrayAdapter<Points> adapter = new ArrayAdapter<>(mContext, R.layout.spinner_item_dropdown, Points.getPointsNames());
        holder.resultPlayer1.setAdapter(adapter);
        holder.resultPlayer2.setAdapter(adapter);
        holder.btnSendResult.setTag(position);

        MatchResult matchResult = match.getResult();
        if(matchResult != null) {
            int spinnerPosition = adapter.getPosition(matchResult.getPlayer1score());
            holder.resultPlayer1.setSelection(spinnerPosition);
            spinnerPosition = adapter.getPosition(matchResult.getPlayer2score());
            holder.resultPlayer2.setSelection(spinnerPosition);
            holder.btnSendResult.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return (mDataList != null) ? mDataList.size() : 0;
    }

    public void setOnItemClickListener (OnViewClickListener listener) {
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
        public void btnSendResultClick(View view) {
            int rowViewPos = (int) btnSendResult.getTag();
            Match currMatch = mDataList.get(rowViewPos);

            if (onItemClickListener != null) {
                onItemClickListener.onButtonClicked(view, currMatch,
                        (Points) resultPlayer1.getSelectedItem(),
                        (Points) resultPlayer2.getSelectedItem());
            }
        }
    }

    /**
     * Interface for listening match list events.
     */
    public interface OnViewClickListener {
        void onButtonClicked(View view, Match match, Points resPlayer1, Points resPlayer2);
    }
}
