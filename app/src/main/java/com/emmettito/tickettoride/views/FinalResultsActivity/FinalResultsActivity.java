package com.emmettito.tickettoride.views.FinalResultsActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.emmettito.models.Player;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

public class FinalResultsActivity extends Activity {
    private Button leaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_final_results);

        leaveButton = (Button) findViewById(R.id.leaveButton);
        leaveButton.setEnabled(true);
        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setFinalResultsRecyclerView();

        Client data = Client.getInstance();
        data.setGame(null);
        //data.getGame().setStarted(false);
        //data.getGame().setGameOver(false);
        //data.getGame().setLastTurn(false);
    }

    @Override
    public void onBackPressed() {}

    private void setFinalResultsRecyclerView() {
//        Client data = Client.getInstance();
//        Game game = new Game();
//        ArrayList<Player> players = new ArrayList<>();
//
//        Player p1 = new Player("Emmett", 0);
//        p1.setColor(PlayerColor.Red);
//        p1.setPoints(50);
//        ArrayList<DestinationCard> cards = new ArrayList<>();
//        DestinationCard card = new DestinationCard(0, 0, null, 1, null, 55);
//        cards.add(card);
//        p1.setDestinationCards(cards);
//        Player p2 = new Player("Marcelo", 1);
//        p2.setColor(PlayerColor.Green);
//        ArrayList<Integer> routes = new ArrayList<>();
//        routes.add(0);
//        p2.setClaimedRoutes(routes);
//        p2.setPoints(50);
//
//        players.add(p1);
//        players.add(p2);
//
//        game.setPlayers(players);
//        data.setGame(game);

        ArrayList<PlayerFinalResults> results = getResults();

        RecyclerView results_view = findViewById(R.id.final_results_view);
        results_view.setLayoutManager(new LinearLayoutManager(this));
        results_view.setAdapter(new FinalResultsAdapter(results));
    }

    private ArrayList<PlayerFinalResults> getResults() {
        Client data = Client.getInstance();
        ArrayList<PlayerFinalResults> results = new ArrayList<>();
        List<Player> players = data.getGame().getPlayers();

        int most_claimed = 0;
        ArrayList<Integer> players_to_award = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            results.add(new PlayerFinalResults(player));

            int num_claimed = player.getClaimedRoutes().size();

            if (num_claimed > most_claimed) {
                most_claimed = num_claimed;
                players_to_award.clear();
                players_to_award.add(i);
            }
            else if (num_claimed == most_claimed) {
                players_to_award.add(i);
            }
        }

        // adds 10 points to the player(s) who claimed the most routes
        for (int i = 0; i < players_to_award.size(); i++) {
            results.get(players_to_award.get(i)).awardPlayerMostClaimedRoutes();
        }

        determineWinner(results);
        return results;
    }

    private void determineWinner(List<PlayerFinalResults> results) {
        int most_points = -999999;
        ArrayList<Integer> winners = new ArrayList<>(); // could have a tie

        // determines the winner(s)
        for (int i = 0; i < results.size(); i++) {
            int points = results.get(i).getPoints();

            if (points > most_points) {
                most_points = points;
                winners.clear();
                winners.add(i);
            }
            else if (points == most_points) {
                winners.add(i);
            }
        }

        for (int i = 0; i < winners.size(); i++) {
            results.get(winners.get(i)).setWinner();
        }
    }
}
