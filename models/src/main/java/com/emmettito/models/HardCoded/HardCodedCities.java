package com.emmettito.models.HardCoded;

import java.util.ArrayList;

public class HardCodedCities {
    private ArrayList<HardCodedCity> cities;

    public HardCodedCities(){
        HardCodedCity city = new HardCodedCity("CityName", (float)0, (float)0, "CityID");
        cities.add(city);

        city = new HardCodedCity("CityName", (float)0, (float)0, "CityID");
        cities.add(city);
    }

    public ArrayList<HardCodedCity> getCities() {
        return cities;
    }
}
