package com.emmettito.models.HardCoded;

import java.util.ArrayList;

public class HardCodedRoutes {
    private ArrayList<HardCodedRoute> routes;

    public HardCodedRoutes(){
        HardCodedSpace space = new HardCodedSpace((float)0, (float)0, (float)0);
        HardCodedRoute route = new HardCodedRoute("CityID", "CityID", "RouteID", space);
        routes.add(route);

        space = new HardCodedSpace((float)0, (float)0, (float)0);
        route = new HardCodedRoute("CityID", "CityID", "RouteID", space);
        routes.add(route);
    }

    public ArrayList<HardCodedRoute> getRoutes() {
        return routes;
    }
}
