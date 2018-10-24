package com.emmettito.models.Cards;

public class TrainCard implements Card{
    /** Variables **/
    private TrainColor color;
    private int cardID;

    /** Constructors **/
    public TrainCard(TrainColor color, int cardID){
        this.color = color;
        this.cardID = cardID;
    }

    /** Getters and Setters **/
    public TrainColor getColor() {
        return color;
    }

    public int getCardID() { return cardID; }

    public void setColor(TrainColor color) {
        this.color = color;
    }

    public void setCardID(int cardID) { this.cardID = cardID; }
}
