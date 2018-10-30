package com.emmettito.models.HardCoded;

import java.util.ArrayList;

public class HardCodedRoutes {
    private ArrayList<HardCodedRoute> routes;

    public HardCodedRoutes(){
        ArrayList<HardCodedSpace> spaces = new ArrayList<>();
        HardCodedSpace space = new HardCodedSpace((float)0, (float)0, (float)0);
        spaces.add(space);
        HardCodedSpace space1 = new HardCodedSpace((float)0, (float)0, (float)0);
        spaces.add(space1);
        HardCodedSpace space2 = new HardCodedSpace((float)0, (float)0, (float)0);
        spaces.add(space2);
        HardCodedRoute route = new HardCodedRoute("CityID", "CityID", "RouteID", spaces);
        routes.add(route);


    }

    public ArrayList<HardCodedRoute> getRoutes() {
        return routes;
    }
}
