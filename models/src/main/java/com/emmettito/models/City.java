package com.emmettito.models;

import java.util.List;

public class City {

    private int ID;
    private String name;
    private List<Integer> routes;
    private Tuple location;

    public City(int ID, String name, List<Integer> routes, Float x, Float y) {
        this.ID = ID;
        this.name = name;
        this.location = new Tuple(x, y);
        this.routes = routes;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getRoutes() {
        return routes;
    }

    public Tuple getLocation() {
        return location;
    }
}
