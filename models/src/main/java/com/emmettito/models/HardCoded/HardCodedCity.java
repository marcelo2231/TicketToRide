package com.emmettito.models.HardCoded;

public class HardCodedCity {
    private String name;
    private Float x;
    private Float y;
    private String id;

    public HardCodedCity(String name, Float x, Float y, String id) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(Float x) {
        x = x;
    }

    public void setY(Float y) {
        y = y;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }
}
