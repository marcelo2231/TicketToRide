package com.emmettito.models;

public class Route {
    private int size;
    private Boolean isDoubleRoute;
    /**
     * private TrainColor color1;
     * private TrainColor color2;
     */
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
}
