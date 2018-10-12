package com.emmettito.models;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.TrainCard;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private int points;
    private int plasticTrains;
    private ArrayList<DestinationCard> destinationCards;
    private ArrayList<TrainCard> trainCards;

    public Player(String playerName){
        this.playerName = playerName;
        this.points = 0;
        plasticTrains = 45;
    }

    public int getPoints() {
        return points;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlasticTrains() {
        return plasticTrains;
    }

    public ArrayList<DestinationCard> getDestinationCards() {
        return destinationCards;
    }

    public ArrayList<TrainCard> getTrainCards() {
        return trainCards;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void claimRoute(int length, int pointValue){
        points += pointValue;
        plasticTrains -= length;
    }

    public void setDestinationCards(ArrayList<DestinationCard> destinationCards) {
        this.destinationCards = destinationCards;
    }

    public void setTrainCards(ArrayList<TrainCard> trainCards) {
        this.trainCards = trainCards;
    }
}
