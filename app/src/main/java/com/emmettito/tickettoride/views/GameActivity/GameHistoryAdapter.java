package com.emmettito.tickettoride.views.GameActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emmettito.tickettoride.R;

import java.util.List;

public class GameHistoryAdapter extends RecyclerView.Adapter<com.emmettito.tickettoride.views.GameActivity.GameHistoryAdapter.GameHistoryViewHolder> {
    private List<String[]> data;
    private String currentPlayer;


    class GameHistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView playerField;
        public TextView commandField;
        public TextView commandDescriptionField;

        public GameHistoryViewHolder(View v) {
            super(v);
            playerField = v.findViewById(R.id.name_field);
            commandField = v.findViewById(R.id.command_field);
            commandDescriptionField = v.findViewById(R.id.command_description_field);
        }

        @Override
        public void onClick(View view) {
        }
    }

    public GameHistoryAdapter(List<String[]> data, String currentPlayer) {
        if (data != null) {
            this.data = data;
        }
        if (currentPlayer != null) {
            this.currentPlayer = currentPlayer;
        }
    }

    @Override
    public com.emmettito.tickettoride.views.GameActivity.GameHistoryAdapter.GameHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_game_history_list, parent, false);

        com.emmettito.tickettoride.views.GameActivity.GameHistoryAdapter.GameHistoryViewHolder view = new com.emmettito.tickettoride.views.GameActivity.GameHistoryAdapter.GameHistoryViewHolder(v);

        return view;
    }

    @Override
    public void onBindViewHolder(com.emmettito.tickettoride.views.GameActivity.GameHistoryAdapter.GameHistoryViewHolder holder, int position) {
        holder.playerField.setText(data.get(position)[0]);
        holder.commandField.setText(data.get(position)[1]);
        holder.commandDescriptionField.setText(data.get(position)[2]);

        if (data.get(position)[0].equals(currentPlayer)) {
            holder.itemView.setBackgroundColor(0xff00ff00);
        } else {
            holder.itemView.setBackgroundColor(0xff00);
        }
    }

    @Override
    public int getItemCount() {
        if (data.size() >= 0) {
            return data.size();
        }
        return -1;
    }
}
