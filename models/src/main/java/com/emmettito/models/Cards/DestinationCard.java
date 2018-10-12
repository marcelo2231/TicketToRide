package com.emmettito.models.Cards;

import com.emmettito.models.City;

public class DestinationCard {
    private City city1;
    private City city2;
    /**
     * Could we make it a Tuple of cities instead?
     */
    private int pointValue;

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }
}
