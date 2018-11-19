package com.emmettito.models.Cards;

import com.emmettito.models.City;
import com.emmettito.models.HardCoded.HardCodedData;
import com.emmettito.models.Tuple;

import java.io.Serializable;


public class DestinationCard implements Card {
    /** Variables **/
    private Tuple cities;
    private Tuple cityIDs;
    private int pointValue;
    private int cardID;

    /** Constructors **/
    public DestinationCard(int cardID, int city1, String city1Name, int city2, String city2Name, int pointValue){
        this.cities = new Tuple(city1Name, city2Name);
        this.cityIDs = new Tuple(city1, city2);
        this.pointValue = pointValue;
        this.cardID = cardID;
    }

    /** Getters and Setters **/
    public Tuple getCities() {
        return cities;
    }

    public Tuple getCityIDs() {
        return cityIDs;
    }

    public int getPointValue() {
        return pointValue;
    }

    public int getCardID() { return cardID; }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(cities.getX());
        string.append(" to ");
        string.append(cities.getY());
        string.append("\n");
        string.append(this.pointValue);
        string.append(" points");
        return string.toString();
    }
}
