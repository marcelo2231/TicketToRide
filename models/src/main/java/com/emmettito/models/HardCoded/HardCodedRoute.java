package com.emmettito.models.HardCoded;

import com.emmettito.models.Tuple;

import java.util.ArrayList;

public class HardCodedRoute {
    private Tuple citiesIds;
    private String id;
    private ArrayList<HardCodedSpace> spaces;

    public HardCodedRoute(String city1, String city2, String id, ArrayList<HardCodedSpace> spaces){
        this.citiesIds = new Tuple(city1, city2);
        this.id = id;
        this.spaces = spaces;
    }

    public Tuple getCitiesIds() {
        return citiesIds;
    }

    public String getId() {
        return id;
    }

    public ArrayList<HardCodedSpace> getSpaces() {
        return spaces;
    }
}
