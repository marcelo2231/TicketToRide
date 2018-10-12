package com.emmettito.models;

public class Tuple {
    private Object x;
    private Object y;

    public Tuple(Object x, Object y){
        this.x = x;
        this.y = y;
    }

    public void setX(Object x){
        this.x = x;
    }

    public void setY(Object y) {
        this.y = y;
    }

    public Object getX() {
        return x;
    }

    public Object getY() {
        return y;
    }
}
