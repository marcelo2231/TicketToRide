package com.emmettito.models;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.TrainCard;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    private String playerName;
    private Color color;
    private int points;
    private int plasticTrains;
    private ArrayList<DestinationCard> destinationCards;
    private ArrayList<TrainCard> trainCards;

    public Player(String playerName){
        this.playerName = playerName;
        this.points = 0;
        plasticTrains = 45;
        this.trainCards = new ArrayList<>();
        destinationCards = new ArrayList<>();

        // Set random color
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        color = new Color(r, g, b);

    }

    public Color getColor() {
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

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPlasticTrains(int plasticTrains) {
        this.plasticTrains = plasticTrains;
    }
}
