package com.emmettito.models;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.TrainCard;

import java.util.ArrayList;

//import java.awt.Color;

public class Player {
    private String playerName;
    private PlayerColor color;
    private int points;
    private int plasticTrains;
    private ArrayList<DestinationCard> destinationCards;
    private ArrayList<TrainCard> trainCards;
    private ArrayList<Integer> claimedRoutes;
    private int position;

    public Player(String playerName, int playerIndex){
        this.playerName = playerName;
        this.points = 0;
        plasticTrains = 45;
        this.trainCards = new ArrayList<>();
        destinationCards = new ArrayList<>();
        this.color = PlayerColor.values()[playerIndex];
        this.position = playerIndex + 1; // Indexes start from 0, position starts from 1
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public PlayerColor getColor() {
        return color;
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

    public ArrayList<Integer> getClaimedRoutes() {
        return claimedRoutes;
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

    public void setClaimedRoutes(ArrayList<Integer> claimedRoutes) {
        this.claimedRoutes = claimedRoutes;
    }

    public void setColor(PlayerColor color) {
        this.color = color;
    }

    public void setPlasticTrains(int plasticTrains) {
        this.plasticTrains = plasticTrains;
    }
}
