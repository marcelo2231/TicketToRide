package com.emmettito.models;

public class Space {

    private Float x;
    private Float y;
    private Float angle;

    public Space(Float x, Float y, Float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public Float getX() {
        return this.x;
    }

    public Float getY() {
        return this.y;
    }

    public Float getAngle() {
        return this.angle;
    }
}
