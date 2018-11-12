///*
//package com.emmettito.tickettoride;
//
//import android.annotation.TargetApi;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.emmettito.models.Cards.DestinationCard;
//import com.emmettito.models.Cards.TrainCard;
//import com.emmettito.models.Game;
//import com.emmettito.models.Player;
//import com.emmettito.tickettoride.views.GameActivity.GameActivity;
//import com.emmettito.tickettoride.views.GameActivity.MapView;
//
//import java.util.ArrayList;
//
//public class TestDriver {
//    private Client client;
//    private GameActivity activity;
//    private Game game;
//    private MapView map;
//
//    public TestDriver(GameActivity activity, Game game, MapView map) {
//        client = Client.getInstance();
//        this.activity = activity;
//        this.game = game;
//        this.map = map;
//    }
//
//    public void runTests() throws java.lang.InterruptedException {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
//
//        AlertDialog dialog = alertDialogBuilder.setMessage("Adding a route owned by an opponent").
//                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        addOpponentRoute();
//                    }
//                }).create();
//
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.show();
//
//        //wait(5000);
//
//        dialog = alertDialogBuilder.setMessage("Adding a route earned by the current player").
//                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        addPlayerRoute();
//                    }
//                }).create();
//
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.show();
//
//        //wait(5000);
//
//        dialog = alertDialogBuilder.setMessage("Updating opponent's destination cards").
//                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        updateOpponentDestinationCards();
//                    }
//                }).create();
//
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.show();
//
//        //wait(5000);
//
//        dialog = alertDialogBuilder.setMessage("Updating opponent's trains").
//                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        updateOpponentTrains();
//                    }
//                }).create();
//
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.show();
//
//        //wait(5000);
//
//        dialog = alertDialogBuilder.setMessage("Updating opponent's train cards").
//                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        updateOpponentTrainCards();
//                    }
//                }).create();
//
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.show();
//
//        //wait(5000);
//
//        dialog = alertDialogBuilder.setMessage("Updating the opponent's points").
//                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        updateOpponentPoints();
//                    }
//                }).create();
//
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.show();
//
//        //wait(5000);
//
//        dialog = alertDialogBuilder.setMessage("Updating train card deck").
//                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        updateInvisibleCards();
//                    }
//                }).create();
//
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.show();
//
//        //wait(5000);
//
//        dialog = alertDialogBuilder.setMessage("Updating face-up train cards").
//                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        updateVisibleTrainCards();
//                    }
//                }).create();
//
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.show();
//
//        //wait(5000);
//
//        dialog = alertDialogBuilder.setMessage("Updating destination card deck").
//                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        updateDestinationCardDeck();
//                    }
//                }).create();
//
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.show();
//
//        //wait(5000);
//
//        //removeDestinationCards();
//        //wait(5000);
//
//        //addDestinationCards();
//        //wait(5000);
//
//        dialog = alertDialogBuilder.setMessage("Removing train cards from the player's hand").
//                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        removeTrainCards();
//                    }
//                }).create();
//
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.show();
//
//        //removeTrainCards();
//        //wait(5000);
//
//        dialog = alertDialogBuilder.setMessage("Adding train cards to the player's hand").
//                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        addTrainCards();
//                    }
//                }).create();
//
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.show();
//
//        //wait(5000);
//
//        dialog = alertDialogBuilder.setMessage("Updating the player's points").
//                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        updatePlayerPoints();
//                    }
//                }).create();
//
//        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.show();
//
//        //wait(5000);
//    }
//
//    private void updatePlayerPoints() {
//        //Toast.makeText(activity, "Updating the player's points", Toast.LENGTH_SHORT).show();
//
//        ArrayList<Player> players = game.getPlayers();
//
//        for (int i = 0; i < game.getPlayers().size(); i++) {
//            //System.out.printf("Old number of points: %d\n", players.get(i).getPoints());
//            if (players.get(i).getPlayerName().equals(client.getUser())) {
//                players.get(i).setPoints(players.get(i).getPoints() + 5);
//            }
//            //sleep(1000);
//
//            //System.out.printf("New number of points: %d\n", players.get(i).getPoints());
//        }
//
//        game.setPlayers(players);
//    }
//
//    private void addTrainCards() {
//        //Toast.makeText(activity, "Adding train cards to the player's hand", Toast.LENGTH_SHORT).show();
//
//        Button trainCard1 = activity.findViewById(R.id.trainCard1);
//        trainCard1.performClick();
//    }
//
//    private void removeTrainCards() {
//        //Toast.makeText(activity, "Removing train cards from the player's hand", Toast.LENGTH_SHORT).show();
//
//        ArrayList<Player> players = game.getPlayers();
//
//        for (int i = 0; i < game.getPlayers().size(); i++) {
//            //System.out.printf("Old number of points: %d\n", players.get(i).getPoints());
//            if (players.get(i).getPlayerName().equals(client.getUser())) {
//                //players.get(i).setPoints(players.get(i).getPoints() + 5);
//
//                ArrayList<TrainCard> cards = players.get(i).getTrainCards();
//
//                activity.removeTrainCardFromPlayer(cards.get(0));
//                cards.remove(0);
//
//                players.get(i).setTrainCards(cards);
//
//                break;
//            }
//            //sleep(1000);
//
//            //System.out.printf("New number of points: %d\n", players.get(i).getPoints());
//        }
//
//        game.setPlayers(players);
//    }
//
//    private void addDestinationCards() {
//        Toast.makeText(activity, "Adding destination cards to the player's hand", Toast.LENGTH_SHORT).show();
//    }
//
//    private void removeDestinationCards() {
//        Toast.makeText(activity, "Removing destination cards from the player's hand", Toast.LENGTH_SHORT).show();
//    }
//
//    private void updateDestinationCardDeck() {
//        //Toast.makeText(activity, "Updating destination card deck", Toast.LENGTH_SHORT).show();
//
//        ArrayList<DestinationCard> deck = (ArrayList) game.getDestinationCardDeck().getAvailableCards();
//
//        deck.remove(0);
//        game.getDestinationCardDeck().setAvailableCards(deck);
//
//        game.setDestinationCardDeck(game.getDestinationCardDeck());
//    }
//
//    private void updateVisibleTrainCards() {
//        //Toast.makeText(activity, "Updating face-up train cards", Toast.LENGTH_SHORT).show();
//
//        Button trainCard1 = activity.findViewById(R.id.trainCard1);
//        Button trainCard2 = activity.findViewById(R.id.trainCard2);
//        Button trainCard3 = activity.findViewById(R.id.trainCard3);
//        Button trainCard4 = activity.findViewById(R.id.trainCard4);
//        Button trainCard5 = activity.findViewById(R.id.trainCard5);
//
//        trainCard1.performClick();
//        trainCard2.performClick();
//        trainCard3.performClick();
//        trainCard4.performClick();
//        trainCard5.performClick();
//    }
//
//    private void updateInvisibleCards() {
//        //Toast.makeText(activity, "Updating train card deck", Toast.LENGTH_SHORT).show();
//
//        ArrayList<TrainCard> deck = (ArrayList) game.getTrainCardDeck().getAvailable();
//
//        deck.remove(0);
//        game.getTrainCardDeck().setAvailable(deck);
//        game.setTrainCardDeck(game.getTrainCardDeck());
//    }
//
//    private void updateOpponentPoints() {
//        //Toast.makeText(activity, "Updating the opponent's points", Toast.LENGTH_SHORT).show();
//
//        ArrayList<Player> players = game.getPlayers();
//
//        for (int i = 0; i < game.getPlayers().size(); i++) {
//            //System.out.printf("Old number of points: %d\n", players.get(i).getPoints());
//            if (!players.get(i).getPlayerName().equals(client.getUser())) {
//                players.get(i).setPoints(players.get(i).getPoints() + 5);
//            }
//            //sleep(1000);
//
//            //System.out.printf("New number of points: %d\n", players.get(i).getPoints());
//        }
//
//        game.setPlayers(players);
//    }
//
//    private void updateOpponentTrainCards() {
//        //Toast.makeText(activity, "Updating opponent's train cards", Toast.LENGTH_SHORT).show();
//
//        ArrayList<Player> players = game.getPlayers();
//
//        for (int i = 0; i < game.getPlayers().size(); i++) {
//            if (!players.get(i).getPlayerName().equals(client.getUser())) {     //If current player is not the user
//               ArrayList<TrainCard> trainCards = players.get(i).getTrainCards();
//
//               ArrayList<TrainCard> deck = (ArrayList) game.getTrainCardDeck().getAvailable();
//
//               TrainCard newCard = deck.remove(0);
//
//               trainCards.add(newCard);
//
//               game.getTrainCardDeck().setAvailable(deck);
//
//               game.setTrainCardDeck(game.getTrainCardDeck());
//
//               players.get(i).setTrainCards(trainCards);
//            }
//            //sleep(1000);
//        }
//        game.setPlayers(players);
//    }
//
//    private void updateOpponentTrains() {
//        //Toast.makeText(activity, "Updating opponent's trains", Toast.LENGTH_SHORT).show();
//
//        ArrayList<Player> players = game.getPlayers();
//
//        for (int i = 0; i < game.getPlayers().size(); i++) {
//            if (!players.get(i).getPlayerName().equals(client.getUser())) {     //If current player is not the user
//                players.get(i).setPlasticTrains(players.get(i).getPlasticTrains() - 5);
//            }
//            //sleep(1000);
//        }
//        game.setPlayers(players);
//    }
//
//    private void updateOpponentDestinationCards() {
//        //Toast.makeText(activity, "Updating opponent's destination cards", Toast.LENGTH_SHORT).show();
//
//        ArrayList<Player> players = game.getPlayers();
//
//        for (int i = 0; i < game.getPlayers().size(); i++) {
//            if (!players.get(i).getPlayerName().equals(client.getUser())) {     //If current player is not the user
//                ArrayList<DestinationCard> destinationCards = (ArrayList) players.get(i).getDestinationCards();
//
//                ArrayList<DestinationCard> deck = (ArrayList) game.getDestinationCardDeck().getAvailableCards();
//
//                DestinationCard newCard = deck.remove(0);
//
//                destinationCards.add(newCard);
//
//                game.getDestinationCardDeck().setAvailableCards(deck);
//
//                game.setDestinationCardDeck(game.getDestinationCardDeck());
//
//                players.get(i).setDestinationCards(destinationCards);
//            }
//            //sleep(1000);
//        }
//        game.setPlayers(players);
//    }
//
//    @TargetApi(24)
//    private void addPlayerRoute() {
//        //Toast.makeText(activity, "Adding a route earned by the current player", Toast.LENGTH_SHORT).show();
//
//        client.addToTakenRoutes(1);
//        client.changeRouteColor(1, game.getOnePlayer(client.getUser()).getColor());
//        map.invalidate();
//    }
//
//    private void addOpponentRoute() {
//        Toast.makeText(activity, "Adding a route owned by an opponent", Toast.LENGTH_SHORT).show();
//
//        String currentUsername = client.getUser();
//
//        for (int i = 0; i < game.getPlayers().size(); i++) {
//            if (!client.getUser().equals(game.getPlayers().get(i).getPlayerName())) {
//                client.setUser(game.getPlayers().get(i).getPlayerName());
//
//                client.addToTakenRoutes(i + 2);
//                client.changeRouteColor(i + 2, game.getOnePlayer(client.getUser()).getColor());
//                map.invalidate();
//            }
//        }
//
//        client.setUser(currentUsername);
//    }
//}
//*/
