package com.emmettito.models.Cards;

import com.emmettito.models.City;
import com.emmettito.models.Tuple;

public class DestinationCard implements Card {
    /** Variables **/
    private Tuple cities;
    private int pointValue;
    private int cardID;

    /** Constructors **/
    public DestinationCard(String cityOne, String cityTwo, int pointValue, int cardID){
        this.cities = new Tuple(cityOne, cityTwo);
        this.pointValue = pointValue;
        this.cardID = cardID;
    }

    /** Getters and Setters **/
    public Tuple getCities() {
        return cities;
    }

    public int getPointValue() {
        return pointValue;
    }

    public int getCardID() { return cardID; }

    public void setCities(Tuple cities) {
        this.cities = cities;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public void setCardID(int cardID) { this.cardID = cardID; }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(cities.getX());
        string.append(" to ");
        string.append(cities.getY());
        return string.toString();
    }
}
