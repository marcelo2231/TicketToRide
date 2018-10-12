package com.emmettito.models;

import java.util.List;

public class City {
    private List<Route> routes;
    private Tuple location;
    private String name;

    public City(List<Route> routes, Tuple location, String name){
        this.location = location;
        this.name = name;
        this.routes = routes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public Tuple getLocation() {
        return location;
    }

    public void setLocation(Tuple location) {
        this.location = location;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
