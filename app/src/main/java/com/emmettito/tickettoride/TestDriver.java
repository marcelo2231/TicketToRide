package com.emmettito.tickettoride;

import android.widget.Toast;

import com.emmettito.tickettoride.views.GameActivity.GameActivity;

public class TestDriver {
    private Client client;
    private GameActivity activity;

    public TestDriver(GameActivity activity) {
        client = Client.getInstance();
        this.activity = activity;
    }

    public void runTests() throws java.lang.InterruptedException {
        updatePlayerPoints();
        //wait(5000);

        //addTrainCards();
        //wait(5000);

        //removeTrainCards();
        //wait(5000);

        //addDestinationCards();
        //wait(5000);

        //removeDestinationCards();
        //wait(5000);

        //updateOpponentTrainCards();
        //wait(5000);

        //updateOpponentTrains();
        //wait(5000);

        //updateOpponentDestinationCards();
        //wait(5000);

        //updateVisibleTrainCards();
        //wait(5000);

        //updateInvisibleCards();
        //wait(5000);

        //updateDestinationCardDeck();
        //wait(5000);

        //addPlayerRoute();
        //wait(5000);

        //addOpponentRoute();
        //wait(5000);
    }

    private void updatePlayerPoints() {
        Toast.makeText(activity, "Updating player's points", Toast.LENGTH_SHORT).show();
    }

    private void addTrainCards() {
        Toast.makeText(activity, "Adding train cards to the player's hand", Toast.LENGTH_SHORT).show();
    }

    private void removeTrainCards() {
        Toast.makeText(activity, "Removing train cards from the player's hand", Toast.LENGTH_SHORT).show();
    }

    private void addDestinationCards() {
        Toast.makeText(activity, "Adding destination cards to the player's hand", Toast.LENGTH_SHORT).show();
    }

    private void removeDestinationCards() {
        Toast.makeText(activity, "Removing destination cards from the player's hand", Toast.LENGTH_SHORT).show();
    }

    private void updateOpponentTrainCards() {
        Toast.makeText(activity, "Updating opponent's train cards", Toast.LENGTH_SHORT).show();
    }

    private void updateOpponentTrains() {
        Toast.makeText(activity, "Updating opponent's trains", Toast.LENGTH_SHORT).show();
    }

    private void updateOpponentDestinationCards() {
        Toast.makeText(activity, "Updating opponent's destination cards", Toast.LENGTH_SHORT).show();
    }

    private void updateVisibleTrainCards() {
        Toast.makeText(activity, "Updating face-up train cards", Toast.LENGTH_SHORT).show();
    }

    private void updateInvisibleCards() {
        Toast.makeText(activity, "Updating train card deck", Toast.LENGTH_SHORT).show();
    }

    private void updateDestinationCardDeck() {
        Toast.makeText(activity, "Updating destination card deck", Toast.LENGTH_SHORT).show();
    }

    private void addPlayerRoute() {
        Toast.makeText(activity, "Adding a route earned by the current player", Toast.LENGTH_SHORT).show();
    }

    private void addOpponentRoute() {
        Toast.makeText(activity, "Adding a route owned by an opponent", Toast.LENGTH_SHORT).show();
    }
}
