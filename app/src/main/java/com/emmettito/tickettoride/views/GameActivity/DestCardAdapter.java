package com.emmettito.tickettoride.views.GameActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.emmettito.tickettoride.R;

import com.emmettito.models.Cards.DestinationCard;

import java.util.List;


public class DestCardAdapter extends RecyclerView.Adapter<DestCardAdapter.DestCardHolder> {
    public class DestCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private DestinationCard mDestinationCard;
        private TextView displayCard;
        @Override
        public void onClick(View view) {

        }

        public DestCardHolder(View v) {
            super(v);
            displayCard = v.findViewById(R.id.display_dest_card_text);
        }

    }

    private List<DestinationCard> destCards;

    public DestCardAdapter(List<DestinationCard> destCards) {
        this.destCards = destCards;
    }


    @Override
    public int getItemCount() {
        return destCards.size();
    }

    @Override
    public DestCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_display_dest_card, parent, false);
        return new DestCardHolder(v);
    }

    @Override
    public void onBindViewHolder(DestCardHolder holder, int position) {
        holder.displayCard.setText(destCards.get(position).toString());
    }


}
