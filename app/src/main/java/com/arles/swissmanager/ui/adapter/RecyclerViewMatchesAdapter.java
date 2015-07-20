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
    private List<Match> mDataList = new ArrayList<>();

    public RecyclerViewMatchesAdapter(Context context, List<Match> list) {
        mContext = context;
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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.match_outcomes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Points> a = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, Points.list());

        holder.resultPlayer1.setAdapter(a);
        holder.resultPlayer2.setAdapter(a);
        holder.btnSendResult.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
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
//            resultPlayer1.getSelectedItem()
            currMatch.reportResult(0,1);
        }
    }
}
