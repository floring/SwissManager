package com.arles.swissmanager.ui.adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.model.Player;
import com.arles.swissmanager.utils.CircleIconDrawable;
import com.arles.swissmanager.utils.ColorGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * RecyclerViewAdapter manages Players data model and adapts it to RecyclerView, which is in MainActivity.
 * Created by Admin on 21.05.2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final int NOTIFY_DELAY = 500;

    private List<Player> mPlayers = new ArrayList<>();
    private OnRecyclerViewClickListener mListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recyclerview_adapter_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mPlayers.get(position).getName());
        holder.setImageDrawable();
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public void addPlayer(final String playerName) {
        final Player player = new Player(playerName);

        // notify of the insertion with a delay, so there is a brief pause after returning
        // from the new book screen; this makes the animation more noticeable
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPlayers.add(player);
                // position == position of last item
                notifyItemInserted(mPlayers.size() - 1);
            }
        }, NOTIFY_DELAY);
    }

    public void removePlayer(final int position) {
        mPlayers.remove(position);

        // notify of the removal with a delay so there is a brief pause after returning
        // from the book details screen; this makes the animation more noticeable
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyItemRemoved(position);
            }
        }, NOTIFY_DELAY);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.text_view_player_name_item) TextView mTextView;
        @InjectView(R.id.image_view_player_item) ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(this);
        }

        public void setImageDrawable() {
            final int imgSize = 40;
            CircleIconDrawable drawable = CircleIconDrawable.builder()
                    .beginConfig()
                    .width(imgSize)
                    .height(imgSize)
                    .endConfig()
                    .buildRound(getIconTitle(), ColorGenerator.MATERIAL.getRandomColor());
            mImageView.setImageDrawable(drawable);
        }

        private String getIconTitle() {
            return String.valueOf(mTextView.getText().charAt(0));
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onRecyclerItemClick(v, getPosition());
            }
        }
    }

    public interface OnRecyclerViewClickListener {
        public void onRecyclerItemClick(View view, int position);
    }
}
