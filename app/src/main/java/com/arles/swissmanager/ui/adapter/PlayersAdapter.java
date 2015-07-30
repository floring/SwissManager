package com.arles.swissmanager.ui.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arles.swissmanager.R;
import com.arles.swissmanager.ui.model.Player;
import com.arles.swissmanager.utils.CircleIconDrawable;
import com.arles.swissmanager.utils.CollectionValidator;
import com.arles.swissmanager.utils.ColorGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * PlayersAdapter manages Players data model and adapts it to RecyclerView, which is in PlayerTabFragment.
 * Created by Admin on 21.05.2015.
 */
public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {
    private final int NOTIFY_DELAY = 500;

    private List<Player> mPlayers = new ArrayList<>();
    private SparseBooleanArray mSelectedItems = new SparseBooleanArray();
    private OnItemClickListener mListener;

    public PlayersAdapter(List<Player> list) {
        mPlayers = list;
    }

    public void setData(List<Player> list) {
        CollectionValidator.validateOnNull(list);
        mPlayers = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recyclerview_player_adapter_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Player playerItem = mPlayers.get(position);
        holder.mTextView.setText(playerItem.getName());
        holder.setImageDrawable(Integer.toString(playerItem.getPrestige()));
        holder.itemView.setActivated(mSelectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        mListener = listener;
    }

    private void addPlayer(final String playerName) {
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

    private void removePlayer(final int position) {
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

    public List<Player> getPlayers() {
        return mPlayers;
    }

    public void setPlayers(List<Player> playersList) {
        mPlayers = playersList;
    }

    public void addNewPlayers(ArrayList<String> nameList) {
        for(int i = 0; i < nameList.size(); ++i) {
            addPlayer(nameList.get(i));
        }
    }

    public void toggleSelection(int position) {
        if(mSelectedItems.get(position, false)) {
            mSelectedItems.delete(position);
        } else {
            mSelectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }

    public void clearSelection() {
        mSelectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return mSelectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(mSelectedItems.size());
        for(int i = 0; i < mSelectedItems.size(); ++i) {
            items.add(mSelectedItems.keyAt(i));
        }
        return items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.text_view_player_name_item) TextView mTextView;
        @InjectView(R.id.image_view_player_item) ImageView mImageView;
        private Integer mColor = null;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        @OnClick(R.id.layout_item_player)
        public void itemClick(View view) {
            if(mListener != null) {
                mListener.onItemClicked(view, getPosition());
            }
        }

        public void setImageDrawable(String imageContent) {
            final int imgSize = 24;
            if(mColor == null) {
                mColor = ColorGenerator.MATERIAL.getRandomColor();
            }
            CircleIconDrawable drawable = CircleIconDrawable.builder()
                    .startConfiguration()
                    .width(imgSize)
                    .height(imgSize)
                    .endConfiguration()
                    .buildRound(imageContent, mColor);
            mImageView.setImageDrawable(drawable);
        }

        private String getIconTitle() {
            return String.valueOf(mTextView.getText().charAt(0));
        }
    }
}
