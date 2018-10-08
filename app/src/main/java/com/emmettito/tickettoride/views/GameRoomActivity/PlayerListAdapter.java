package com.emmettito.tickettoride.views.GameRoomActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emmettito.tickettoride.R;

import java.util.List;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerListViewHolder> {
    private List<String> data;

    class PlayerListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView playerField;

        public PlayerListViewHolder(View v) {
            super(v);

            playerField = v.findViewById(R.id.player_name);
        }

        @Override
        public void onClick(View view) {}
    }

    public PlayerListAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public PlayerListAdapter.PlayerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_player_list, parent, false);

        PlayerListAdapter.PlayerListViewHolder view = new PlayerListAdapter.PlayerListViewHolder(v);

        return view;
    }

    @Override
    public void onBindViewHolder(PlayerListAdapter.PlayerListViewHolder holder, int position) {
        holder.playerField.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
