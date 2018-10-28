package com.emmettito.tickettoride.views.GameActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emmettito.tickettoride.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<com.emmettito.tickettoride.views.GameActivity.ChatAdapter.ChatViewHolder> {
    private List<String[]> data;
    private String currentPlayer;

    class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView playerField;
        public TextView messageField;

        public ChatViewHolder(View v) {
            super(v);
            playerField = v.findViewById(R.id.name_field);
            messageField = v.findViewById(R.id.message_field);
        }

        @Override
        public void onClick(View view) {}
    }

    public ChatAdapter(List<String[]> data, String currentPlayer) {
        this.data = data;
        this.currentPlayer = currentPlayer;
    }

    @Override
    public com.emmettito.tickettoride.views.GameActivity.ChatAdapter.ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_chat_list, parent, false);

        com.emmettito.tickettoride.views.GameActivity.ChatAdapter.ChatViewHolder view = new com.emmettito.tickettoride.views.GameActivity.ChatAdapter.ChatViewHolder(v);

        return view;
    }

    @Override
    public void onBindViewHolder(com.emmettito.tickettoride.views.GameActivity.ChatAdapter.ChatViewHolder holder, int position) {
        holder.playerField.setText(data.get(position)[0]);
        holder.messageField.setText(data.get(position)[1]);

        if (data.get(position)[0].equals(currentPlayer)) {
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
