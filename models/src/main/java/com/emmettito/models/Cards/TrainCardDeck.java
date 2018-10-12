package com.emmettito.models.Cards;

import java.util.List;

public class TrainCardDeck implements Deck {
    private List<TrainCard> available;
    private List<TrainCard> discardPile;

    @Override
    public void shuffle() {

    }

    @Override
    public void addCardToBottom() {

    }

    public void setDiscardPile(List<TrainCard> discardPile) {
        this.discardPile = discardPile;
    }

    public List<TrainCard> getAvailable() {
        return available;
    }

    public List<TrainCard> getDiscardPile() {
        return discardPile;
    }

    public void setAvailable(List<TrainCard> available) {
        this.available = available;
    }

    public int getSizeAvailable(){
        return available.size();
    }

    public TrainCard drawCard(){
        //returns the next available card if possible
        return null;
    }
}
