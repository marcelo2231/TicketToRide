package com.emmettito.models.HardCoded;

import com.emmettito.models.Tuple;

import java.util.ArrayList;

public class HardCodedRoute {
    private Tuple citiesIds;
    private String id;
    private HardCodedSpace space;

    public HardCodedRoute(String city1, String city2, String id, HardCodedSpace space){
        this.citiesIds = new Tuple(city1, city2);
        this.id = id;
        this.space = space;
    }

    public Tuple getCitiesIds() {
        return citiesIds;
    }

    public String getId() {
        return id;
    }

    public HardCodedSpace getSpace() {
        return space;
    }
}
