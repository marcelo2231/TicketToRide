package com.emmettito.models.Cards;

import com.emmettito.models.City;
import com.emmettito.models.Tuple;

public class DestinationCard implements Card {
    /** Variables **/
    private Tuple cities;
    private int pointValue;

    /** Constructors **/
    public DestinationCard(String cityOne, String cityTwo, int pointValue){
        this.cities = new Tuple(cityOne, cityTwo);
        this.pointValue = pointValue;
    }

    /** Getters and Setters **/

    public Tuple getCities() {
        return cities;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setCities(Tuple cities) {
        this.cities = cities;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }
}
