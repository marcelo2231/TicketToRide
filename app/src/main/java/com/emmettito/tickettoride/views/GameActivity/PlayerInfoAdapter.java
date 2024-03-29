package com.emmettito.tickettoride.views.GameActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emmettito.tickettoride.R;

import java.util.List;

public class PlayerInfoAdapter extends RecyclerView.Adapter<PlayerInfoAdapter.PlayerInfoHolder>  {
    private List<String[]> data;

    private int selectedPosition = RecyclerView.NO_POSITION;

    class PlayerInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout player_color;
        public TextView player_name;
        public TextView player_points;
        public TextView player_position;
        public TextView player_train_cards;
        public TextView player_dest_cards;
        public TextView player_trains;

        public PlayerInfoHolder(View v) {
            super(v);
            player_color = v.findViewById(R.id.player_background);
            player_name = v.findViewById(R.id.player_name);
            player_points = v.findViewById(R.id.player_points);
            //player_position = v.findViewById(R.id.player_position);
            player_train_cards = v.findViewById(R.id.player_train_cards);
            player_dest_cards = v.findViewById(R.id.player_dest_cards);
            player_trains = v.findViewById(R.id.player_trains);

            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            notifyItemChanged(selectedPosition);
            selectedPosition = getLayoutPosition();
            notifyItemChanged(selectedPosition);
        }
    }

    public PlayerInfoAdapter(List<String[]> data) {
        this.data = data;

    }

    @Override
    public PlayerInfoAdapter.PlayerInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_playerinfo_list, parent, false);

        PlayerInfoHolder view = new PlayerInfoHolder(v);

        return view;
    }

    @Override
    public void onBindViewHolder(PlayerInfoHolder holder, int position) {
        switch (data.get(position)[0]) {
            case "Red":
                holder.player_color.setBackgroundResource(R.color.redPlayer);
                break;
            case "Green":
                holder.player_color.setBackgroundResource(R.color.greenPlayer);
                break;
            case "Blue":
                holder.player_color.setBackgroundResource(R.color.bluePlayer);
                break;
            case "Orange":
                holder.player_color.setBackgroundResource(R.color.orangePlayer);
                break;
            case "Yellow":
                holder.player_color.setBackgroundResource(R.color.yellowPlayer);
                break;
        }
        holder.player_points.setText("  " + data.get(position)[2]);
        //holder.player_position.setText(data.get(position)[3]);
        holder.player_train_cards.setText(data.get(position)[4]);
        holder.player_dest_cards.setText(data.get(position)[5]);
        holder.player_trains.setText("  " + data.get(position)[6]);
        if (data.get(position)[7].equals(data.get(position)[3])) // if playerTurn == position
        {
            holder.player_name.setText("  *" + data.get(position)[1] + "*");
        }else{
            holder.player_name.setText("  " + data.get(position)[1]);
        }

        holder.itemView.setSelected(selectedPosition == position);

    }

    @Override
    public int getItemCount() {
        if (data == null){
            return 0;
        }
        return data.size();
    }
}
