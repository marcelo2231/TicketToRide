package com.emmettito.models;

import com.emmettito.models.Cards.DestinationCardDeck;
import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainCardDeck;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.util.ArrayList;

public class Game {
    /** Variables **/
    String gameName;
    ArrayList<Player> players;
    DestinationCardDeck destinationCardDeck;
    TrainCardDeck trainCardDeck;
    ArrayList<TrainCard> faceUpCards;

    public Game(){
        players = new ArrayList<>();
    }

    /** Setters **/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setDestinationCardDeck(DestinationCardDeck destinationCardDeck) {
        this.destinationCardDeck = destinationCardDeck;
    }

    public void setFaceUpCards(ArrayList<TrainCard> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }

    public void setTrainCardDeck(TrainCardDeck trainCardDeck) {
        this.trainCardDeck = trainCardDeck;
    }

    /** Getters **/
    public String getGameName() {
        return gameName;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<TrainCard> getFaceUpCards() {
        return faceUpCards;
    }

    public DestinationCardDeck getDestinationCardDeck() {
        return destinationCardDeck;
    }

    public TrainCardDeck getTrainCardDeck() {
        return trainCardDeck;
    }

    public Player getOnePlayer(String userName) {
        boolean found = false;
        int index = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerName().equals(userName));
            index = i;
            found = true;
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
        int index = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerName().equals(userName));
            index = i;
            found = true;
        }
        return found;
    }

    public void removePlayer(String userName) {
        boolean found = false;
        int index = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerName().equals(userName));
            index = i;
            found = true;
        }
        if (found) {
            players.remove(index);
        }
    }


}
