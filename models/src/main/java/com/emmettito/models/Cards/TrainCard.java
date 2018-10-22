package com.emmettito.models.Cards;

public class TrainCard implements Card{
    private TrainColor color;

    public TrainCard(TrainColor color){
        this.color = color;
    }

    public TrainColor getColor() {
        return color;
    }

    public void setColor(TrainColor color) {
        this.color = color;
    }
}
