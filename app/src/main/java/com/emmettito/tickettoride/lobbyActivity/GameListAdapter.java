package com.emmettito.tickettoride.lobbyActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emmettito.tickettoride.R;

import java.util.List;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameListViewHolder> {
    private List<String[]> data;

    private int selectedPosition = RecyclerView.NO_POSITION;

    private Button joinButton;

    class GameListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView gameField;
        public TextView playerField;
        public TextView statusField;

        public GameListViewHolder(View v) {
            super(v);
            gameField = v.findViewById(R.id.game_field);
            playerField = v.findViewById(R.id.person_field);
            statusField = v.findViewById(R.id.status_field);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            notifyItemChanged(selectedPosition);
            selectedPosition = getLayoutPosition();
            notifyItemChanged(selectedPosition);

            //Button joinButton = (Button) view.findViewById(R.id.joinbutton);
            joinButton.setEnabled(true);
        }
    }

    public GameListAdapter(List<String[]> data, Button joinButton) {
        this.data = data;
        this.joinButton = joinButton;
    }

    @Override
    public GameListAdapter.GameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_game_list, parent, false);

        GameListViewHolder view = new GameListViewHolder(v);

        return view;
    }

    @Override
    public void onBindViewHolder(GameListViewHolder holder, int position) {
        holder.gameField.setText(data.get(position)[0]);
        holder.playerField.setText(data.get(position)[1]);
        holder.statusField.setText(data.get(position)[2]);

        holder.itemView.setSelected(selectedPosition == position);

        if (holder.itemView.isSelected()) {
            holder.itemView.setBackgroundColor(0xff00ff00);
        }
        else {
            holder.itemView.setBackgroundColor(0xff00);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
