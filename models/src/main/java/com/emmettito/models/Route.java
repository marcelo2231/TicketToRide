package com.emmettito.models;

import com.emmettito.models.Cards.TrainColor;

public class Route {
    private int size;
    private Boolean isDoubleRoute;
    private TrainColor color1;
    private TrainColor color2;
    private Tuple cities;

    public Boolean getDoubleRoute() {
        return isDoubleRoute;
    }

    public int getSize() {
        return size;
    }

    public Tuple getCities() {
        return cities;
    }

    public void setCities(Tuple cities) {
        this.cities = cities;
    }

    public void setDoubleRoute(Boolean doubleRoute) {
        isDoubleRoute = doubleRoute;
    }

    public void setSize(int size) {
        this.size = size;
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
}
