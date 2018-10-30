package com.emmettito.tickettoride;

import android.widget.Button;
import android.widget.Toast;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;

import java.util.ArrayList;

public class TestDriver {
    private Client client;
    private GameActivity activity;
    private Game game;

    public TestDriver(GameActivity activity, Game game) {
        client = Client.getInstance();
        this.activity = activity;
        this.game = game;
    }

    public void runTests() throws java.lang.InterruptedException {
        updatePlayerPoints();
        //wait(5000);

        //updateOpponentPoints();
        //wait(5000);

        //addTrainCards();
        //wait(5000);

        //removeTrainCards();
        //wait(5000);

        //addDestinationCards();
        //wait(5000);

        //removeDestinationCards();
        //wait(5000);

        updateVisibleTrainCards();
        //wait(5000);

        updateInvisibleCards();
        //wait(5000);

        updateOpponentTrainCards();
        //wait(5000);

        updateOpponentTrains();
        //wait(5000);

        updateOpponentDestinationCards();
        //wait(5000);

        //updateDestinationCardDeck();
        //wait(5000);

        //addPlayerRoute();
        //wait(5000);

        //addOpponentRoute();
        //wait(5000);
    }

    private void updatePlayerPoints() throws java.lang.InterruptedException {
        Toast.makeText(activity, "Updating player's points", Toast.LENGTH_SHORT).show();

        ArrayList<Player> players = game.getPlayers();

        for (int i = 0; i < game.getPlayers().size(); i++) {
            System.out.printf("Old number of points: %d\n", players.get(i).getPoints());
            players.get(i).setPoints(players.get(i).getPoints() + 5);
            //sleep(1000);

            System.out.printf("New number of points: %d\n", players.get(i).getPoints());
        }

        game.setPlayers(players);
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

    private void updateVisibleTrainCards() {
        Toast.makeText(activity, "Updating face-up train cards", Toast.LENGTH_SHORT).show();

        Button trainCard1 = activity.findViewById(R.id.trainCard1);
        Button trainCard2 = activity.findViewById(R.id.trainCard2);
        Button trainCard3 = activity.findViewById(R.id.trainCard3);
        Button trainCard4 = activity.findViewById(R.id.trainCard4);
        Button trainCard5 = activity.findViewById(R.id.trainCard5);

        trainCard1.performClick();
        trainCard2.performClick();
        trainCard3.performClick();
        trainCard4.performClick();
        trainCard5.performClick();
    }

    private void updateInvisibleCards() {
        Toast.makeText(activity, "Updating train card deck", Toast.LENGTH_SHORT).show();

        ArrayList<TrainCard> deck = (ArrayList) game.getTrainCardDeck().getAvailable();

        deck.remove(0);
        game.getTrainCardDeck().setAvailable(deck);
        game.setTrainCardDeck(game.getTrainCardDeck());
    }

    private void updateOpponentTrainCards() {
        Toast.makeText(activity, "Updating opponent's train cards", Toast.LENGTH_SHORT).show();

        ArrayList<Player> players = game.getPlayers();

        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (!players.get(i).getPlayerName().equals(client.getUser())) {     //If current player is not the user
               ArrayList<TrainCard> trainCards = players.get(i).getTrainCards();

               ArrayList<TrainCard> deck = (ArrayList) game.getTrainCardDeck().getAvailable();

               TrainCard newCard = deck.remove(0);

               trainCards.add(newCard);

               game.getTrainCardDeck().setAvailable(deck);

               game.setTrainCardDeck(game.getTrainCardDeck());

               players.get(i).setTrainCards(trainCards);
            }
            //sleep(1000);
        }
        game.setPlayers(players);
    }

    private void updateOpponentTrains() {
        Toast.makeText(activity, "Updating opponent's trains", Toast.LENGTH_SHORT).show();

        ArrayList<Player> players = game.getPlayers();

        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (!players.get(i).getPlayerName().equals(client.getUser())) {     //If current player is not the user
                players.get(i).setPlasticTrains(players.get(i).getPlasticTrains() - 5);
            }
            //sleep(1000);
        }
        game.setPlayers(players);
    }

    private void updateOpponentDestinationCards() {
        Toast.makeText(activity, "Updating opponent's destination cards", Toast.LENGTH_SHORT).show();

        ArrayList<Player> players = game.getPlayers();

        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (!players.get(i).getPlayerName().equals(client.getUser())) {     //If current player is not the user
                ArrayList<DestinationCard> destinationCards = players.get(i).getDestinationCards();

                ArrayList<DestinationCard> deck = (ArrayList) game.getDestinationCardDeck().getAvailableCards();

                DestinationCard newCard = deck.remove(0);

                destinationCards.add(newCard);

                game.getDestinationCardDeck().setAvailableCards(deck);

                game.setDestinationCardDeck(game.getDestinationCardDeck());

                players.get(i).setDestinationCards(destinationCards);
            }
            //sleep(1000);
        }
        game.setPlayers(players);
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
