package com.emmettito.models;

import com.emmettito.models.Cards.DestinationCardDeck;
import com.emmettito.models.Cards.TrainCardDeck;

import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable {
    /** Variables **/
    private String gameName;
    private ArrayList<Player> players;
    private DestinationCardDeck destinationCardDeck;
    private TrainCardDeck trainCardDeck;
    private ArrayList<Tuple> Chat;
    private int playerTurnIndex; // Index of player on ArrayList<Player> players who has the turn
    //private Tuple longestPath; //Tuple(length, Player)

    private static Game instance;

    private Game() {
        players = new ArrayList<>();
        destinationCardDeck = new DestinationCardDeck();
        trainCardDeck = new TrainCardDeck();
        Chat = new ArrayList<>();
        playerTurnIndex = 0;
    }

    public static Game getInstance()
    {
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }

    /** Setters **/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;

        setChanged();
        notifyObservers(players);
    }

    public void setDestinationCardDeck(DestinationCardDeck destinationCardDeck) {
        this.destinationCardDeck = destinationCardDeck;

        setChanged();
        notifyObservers(destinationCardDeck);
    }

    public void setTrainCardDeck(TrainCardDeck trainCardDeck) {
        this.trainCardDeck = trainCardDeck;

        setChanged();
        notifyObservers(trainCardDeck);
    }

    public void setChat(ArrayList<Tuple> chat) {
        Chat = chat;
    }

    /** Getters **/
    public String getGameName() {
        return gameName;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public DestinationCardDeck getDestinationCardDeck() {
        return destinationCardDeck;
    }

    public TrainCardDeck getTrainCardDeck() {
        return trainCardDeck;
    }

    public ArrayList<Tuple> getChat() {

        return Chat;
    }

    public int getPlayerTurnIndex() {
        return playerTurnIndex;
    }

    public Player getOnePlayer(String userName) {
        boolean found = false;
        int index = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerName().equals(userName)){
                index = i;
                found = true;
                //break;
            }
        }
        if (found) {
            return players.get(index);
        }
        else {
            return null;
        }
    }

    public boolean playerInGame(String userName) {
        boolean found = false;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerName().equals(userName)) {
                found = true;
                //break;
            }
        }
        return found;
    }

    public void removePlayer(String userName) {
        boolean found = false;
        int index = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerName().equals(userName)) {
                index = i;
                found = true;
                //break;
            }
        }
        if (found) {
            players.remove(index);
        }
    }

    public void incrementTurnIndex(){
        playerTurnIndex++;
        if(playerTurnIndex >= players.size()){
            playerTurnIndex = 0;
        }
    }

    public boolean isPlayerTurn(Player player){
       if (playerTurnIndex == players.indexOf(player)){
           return true;
       }
       return false;
    }
}
