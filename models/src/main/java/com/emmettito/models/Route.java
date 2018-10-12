package com.emmettito.models;

import com.emmettito.models.Cards.TrainColor;

public class Route {
    private int size;
    private Boolean isDoubleRoute;
    private TrainColor color1;
    private TrainColor color2;
    private Tuple cities;
    private int pointValue;

    public Boolean getDoubleRoute() {
        return isDoubleRoute;
    }

    public int getSize() {
        return size;
    }

    public Tuple getCities() {
        return cities;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setCities(Tuple cities) {
        this.cities = cities;
    }

    public void setDoubleRoute(Boolean doubleRoute) {
        isDoubleRoute = doubleRoute;
    }

    public void setSize(int size) {
        this.size = size;
        calculatePointValue(size);
    }

    public TrainColor getColor1() {
        return color1;
    }

    public TrainColor getColor2() {
        return color2;
    }

    public void setColor1(TrainColor color1) {
        this.color1 = color1;
    }

    public void setColor2(TrainColor color2) {
        this.color2 = color2;
    }

    private void calculatePointValue(int length){
        //longest route length is 6
        switch (length){
            case 1: pointValue = 1;
            case 2: pointValue = 2;
            case 3: pointValue = 4;
            case 4: pointValue = 7;
            case 5: pointValue = 10;
            case 6: pointValue = 15;
            default: break;
        }
    }
}
