package com.emmettito.models;

import java.util.List;

public class Map {
    private List<City> cities;
    private List<Route> routes;

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getCities() {
        return cities;
    }
}
