package com.emmettito.tickettoride.views.GameActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emmettito.tickettoride.R;

import java.util.List;

/**
 *
 * ChatAdapter.java
 *
 * The ChatAdapter is the RecyclerView corresponding to the Chat Adapter.
 * It contains a list of ViewHolders with the chat history. It will display all the chat data
 * sent ton this recyclerView.
 *
 * Domain:
 *      data: List of String Array, contains the chat data to be displayed on each view holder
 *      currentPlayer: String, player that has recycler view
 *
 * ChatAdapter extends RecyclerView.Adapter.
 *
 * @author  Marcelo Archiza Almeida
 * @since   2018
 *
 * @invariant data refers to the list of strings that will be added on each view holder
 * @invariant currentPlayer refers to the name of the current player
 *
 */
public class ChatAdapter extends RecyclerView.Adapter<com.emmettito.tickettoride.views.GameActivity.ChatAdapter.ChatViewHolder> {
    private List<String[]> data;
    private String currentPlayer;

    /**
     * ChatViewHolder
     *
     * This ChatViewHolder is a RecyclerView ViewHolder that represents a view holder
     * for ChatView RecyclerView.
     *
     * @invariant playerField refers to an actual GUI text view
     * @invariant messageField refers to an actual GUI text view
     */
    class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView playerField;
        public TextView messageField;

        public ChatViewHolder(View v) {
            super(v);
            playerField = v.findViewById(R.id.name_field);
            messageField = v.findViewById(R.id.message_field);
        }
        /**
         * onClick
         *
         * This method defines the behavior of the Android click functionality.
         *
         * @param view refers to the view object
         *
         * @pre None
         *
         * @post None
         *
         *
         */
        @Override
        public void onClick(View view) {}
    }

    /**
     * ChatAdatpter
     *
     * This Constructor initializes the class and it sets the data and currentPlayer
     * equal to the variables sent to it.
     *
     * @param data refers to the list of strings that will be added on each view holder
     * @param currentPlayer refers to the name of the current player
     *
     * @pre data is not null
     * @pre currentPlayer is not null
     *
     * @post None
     *
     *
     */
    public ChatAdapter(List<String[]> data, String currentPlayer) {
        if (data != null) {
            this.data = data;
        }
        if(currentPlayer != null) {
            this.currentPlayer = currentPlayer;
        }
    }

    /**
     * onCreateViewHolder
     *
     * This method defines the behavior of the creation of view holder and overridden
     * from the RecyclerView parent class. This implementation Inflates the layout.
     *
     * @param parent refers to the view that holds the recycler view
     * @param viewType refers to the view type id
     *
     * @pre None
     *
     * @post layout is inflated
     *
     *
     */
    @Override
    public com.emmettito.tickettoride.views.GameActivity.ChatAdapter.ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_chat_list, parent, false);

        com.emmettito.tickettoride.views.GameActivity.ChatAdapter.ChatViewHolder view = new com.emmettito.tickettoride.views.GameActivity.ChatAdapter.ChatViewHolder(v);

        return view;
    }

    /**
     * onBindViewHolder
     *
     * This method defines the behavior of the creation of view holder and overridden
     * from the RecyclerView parent class. This implementation Inflates the layout.
     *
     * @param holder refers to the chat view holder
     * @param position refers to the position on the recycler view
     *
     * @pre 0 < position < data.size()
     * @pre holder is not null
     *
     * @post holder playerField was set
     * @post holder messageField was set
     *
     */
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

    /**
     * getItemCount
     *
     * This method returns the amount of viewHolders on this recyclerView.
     *
     * @pre data is not null
     *
     * @post size is greater or equal to 0
     *
     */
    @Override
    public int getItemCount() {
        if (data.size() >= 0) {
            return data.size();
        }
        return -1;
    }
}
