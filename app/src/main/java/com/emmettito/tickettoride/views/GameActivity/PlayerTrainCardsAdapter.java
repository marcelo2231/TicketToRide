package com.emmettito.tickettoride.views.GameActivity;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.models.Tuple;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

public class PlayerTrainCardsAdapter extends RecyclerView.Adapter<PlayerTrainCardsAdapter.PlayerTrainCardsHolder>  {

    private Client data;
    private List<Tuple> cards_sorted;

    class PlayerTrainCardsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout train_card;
        public TextView text;

        public TrainColor color;
        public int count;

        public PlayerTrainCardsHolder(View v) {
            super(v);
            train_card = v.findViewById(R.id.train_card);
            text = v.findViewById(R.id.train_card_count);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.w("adapter", color.toString());
            data.setTempColorChoice(color);
            Toast.makeText(view.getContext(), "You've selected the color " + color.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public PlayerTrainCardsAdapter(List<Tuple> playerTrainCards) {
        cards_sorted = playerTrainCards;
        data = Client.getInstance();
    }

    @Override
    public PlayerTrainCardsAdapter.PlayerTrainCardsHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_player_train_cards, viewGroup, false);
        return new PlayerTrainCardsHolder(v);
    }

    @Override
    public void onBindViewHolder(PlayerTrainCardsHolder holder, int position) {
        Tuple t = cards_sorted.get(position);
        TrainColor color = (TrainColor)t.getX();
        int count = (int)t.getY();

        holder.color = color;
        holder.count = count;

        int trainColor;

        switch (color) {
            case Red: trainColor = R.drawable.red_train_card; break;
            case Pink: trainColor = R.drawable.pink_train_card; break;
            case Orange: trainColor = R.drawable.orange_train_card; break;
            case Yellow: trainColor = R.drawable.yellow_train_card; break;
            case Green: trainColor = R.drawable.green_train_card; break;
            case Blue: trainColor = R.drawable.blue_train_card; break;
            case White: trainColor = R.drawable.white_train_card; break;
            case Black: trainColor = R.drawable.black_train_card; break;
            case Wild: trainColor = R.drawable.wild_train_card; break;
            default: trainColor = R.drawable.back_of_train_card; break;
        }

        Drawable trainImage = holder.train_card.getResources().getDrawable(trainColor);
        holder.train_card.setBackground(trainImage);
        holder.text.setText(String.valueOf(count));

    }

    @Override
    public int getItemCount() {
        return cards_sorted.size();
    }
}

