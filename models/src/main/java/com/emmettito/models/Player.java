package com.emmettito.models;

public class Player {
    private String name;
    private int points;

    public Player(String name){
        this.name = name;
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
