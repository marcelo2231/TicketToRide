package com.emmettito.tickettoride.views.FinalResultsActivity;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emmettito.models.Cards.TrainColor;
import com.emmettito.models.PlayerColor;
import com.emmettito.models.Tuple;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;

import java.util.List;

public class FinalResultsAdapter extends RecyclerView.Adapter<FinalResultsAdapter.FinalResultsHolder>  {

    private List<PlayerFinalResults> results;

    class FinalResultsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout player_results;

        public TextView player_name;
        public TextView winner_indicator;
        public TextView player_points;

        public TextView points_from_claimed_routes;
        public TextView points_from_reached_dest;
        public TextView points_from_unreached_dest;
        public TextView points_from_most_claimed_routes;

        public FinalResultsHolder(View view) {
            super(view);
            player_results = view.findViewById(R.id.player_results);

            player_name = view.findViewById(R.id.player_name);
            winner_indicator = view.findViewById(R.id.winner_indicator);
            player_points = view.findViewById(R.id.player_points);

            points_from_claimed_routes = view.findViewById(R.id.points_from_claimed_routes);
            points_from_reached_dest = view.findViewById(R.id.points_from_reached_dest);
            points_from_unreached_dest = view.findViewById(R.id.points_from_unreached_dest);
            points_from_most_claimed_routes = view.findViewById(R.id.points_from_most_claimed_routes);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {}
    }

    public FinalResultsAdapter(List<PlayerFinalResults> results) {
        this.results = results;
    }

    @Override
    public FinalResultsAdapter.FinalResultsHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_final_results, viewGroup, false);
        return new FinalResultsHolder(linearLayout);
    }

    private int getBackgroundColor(PlayerColor color) {
        switch (color) {
            case Red:
                return R.color.redPlayer;
            case Green:
                return R.color.greenPlayer;
            case Blue:
                return R.color.bluePlayer;
            case Orange:
                return R.color.orangePlayer;
            case Yellow:
                return R.color.yellowPlayer;
        }
        return R.color.defaultPlayerColor;
    }

    @Override
    public void onBindViewHolder(FinalResultsHolder holder, int position) {
        PlayerFinalResults player = results.get(position);
        int color = getBackgroundColor(player.getColor());

        holder.player_results.setBackgroundResource(color);
        holder.player_name.setText(player.getName());

        if (!player.isWinner()) {
            holder.winner_indicator.setText("");
        }

        holder.player_points.setText(String.valueOf(player.getPoints()));
        holder.points_from_claimed_routes.setText(String.valueOf(player.getPointsFromClaimedRoutes()));
        holder.points_from_reached_dest.setText(String.valueOf(player.getPointsFromReachedDest()));
        holder.points_from_unreached_dest.setText(String.valueOf(player.getPointsFromUnreachedDest()));

        if (player.hasMostClaimedRoutesAward()) {
            holder.points_from_most_claimed_routes.setText("10");
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}

