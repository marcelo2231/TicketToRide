package com.emmettito.models;


import com.emmettito.models.Cards.TrainColor;

import java.util.List;

public class Route {

    private int ID;
    private Tuple cities;
    private List<Space> spaces;
    private int pointValue;
    private Boolean isDoubleRoute;
    private TrainColor trainColor; //the required train color
    private PlayerColor playerColor; //the color of the player who played there

    public Route(int ID, Tuple cities, List<Space> spaces, boolean isDoubleRoute, TrainColor c1, PlayerColor c2) {
        this.ID = ID;
        this.cities = cities;
        this.spaces = spaces;
        this.isDoubleRoute = isDoubleRoute;
        this.trainColor = c1;
        this.playerColor = c2;

        calculatePointValue(spaces.size());
    }

    public int getID() {
        return ID;
    }

    public Tuple getCities() {
        return cities;
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public int getPointValue() {
        return pointValue;
    }

    public Boolean isDoubleRoute() {
        return isDoubleRoute;
    }

    public TrainColor getTrainColor() {
        return trainColor;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    private int calculatePointValue(int length){

        //longest route length is 6
        switch (length){
            case 1: return 1;
            case 2: return 2;
            case 3: return 4;
            case 4: return 7;
            case 5: return 10;
            case 6: return 15;
            default: return -1;
        }
    }
}

