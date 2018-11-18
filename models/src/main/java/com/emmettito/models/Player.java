package com.emmettito.models;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainColor;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String playerName;
    private PlayerColor color;
    private int points;
    private int plasticTrains;
    private ArrayList<DestinationCard> destinationCards;
    private ArrayList<TrainCard> trainCards;
    private ArrayList<Tuple> indexedCards;
    private ArrayList<Integer> claimedRoutes;
    private int position;

    public Player(String playerName, int playerIndex){
        this.playerName = playerName;
        this.points = 0;
        this.plasticTrains = 45;
        this.trainCards = new ArrayList<>();
        this.destinationCards = new ArrayList<>();
        this.indexedCards = new ArrayList<>();
        this.claimedRoutes = new ArrayList<>();
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

    private int numOfColor(TrainColor color) {
        int num = 0;

        for (int i = 0; i < trainCards.size(); i++) {
            if (trainCards.get(i).getColor() == color) {
                num++;
            }
        }

        return num;
    }

    public List<Tuple> getIndexedCards() {
        return indexedCards;
    }

    public void reIndexCards() {
        ArrayList<Tuple> new_index = new ArrayList<>();

        for (TrainColor color : TrainColor.values()) {
            int num_color = numOfColor(color);

            if (num_color > 0) {
                new_index.add(new Tuple(color, num_color));
            }
        }

        indexedCards.clear();
        indexedCards.addAll(new_index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + playerName + ", Points: " + points + ", Trains: " + plasticTrains + ", # Claimed Routes: " + claimedRoutes.size());
        return sb.toString();
    }
}
