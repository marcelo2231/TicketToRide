package com.emmettito.models.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainCardDeck implements Deck {
    private List<TrainCard> available;
    private List<TrainCard> discardPile;

    public TrainCardDeck(){
        available = new ArrayList<>();
        discardPile = new ArrayList<>();

        available.add(new TrainCard(TrainColor.Wild));
        for(int i = 0; i <= 12; i++){
            available.add(new TrainCard(TrainColor.Red));
            available.add(new TrainCard(TrainColor.Orange));
            available.add(new TrainCard(TrainColor.Yellow));
            available.add(new TrainCard(TrainColor.Green));
            available.add(new TrainCard(TrainColor.Blue));
            available.add(new TrainCard(TrainColor.Black));
            available.add(new TrainCard(TrainColor.White));
            available.add(new TrainCard(TrainColor.Pink));
            available.add(new TrainCard(TrainColor.Wild));
        }
        available.add(new TrainCard(TrainColor.Wild));

        shuffle();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(available);
        Collections.shuffle(discardPile);
    }

    @Override
    public void addCardToBottom(Card cardToAdd) {
        TrainCard card = (TrainCard) cardToAdd;
        available.add(card);
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

    @Override
    public TrainCard drawCard()throws Exception{
        TrainCard top;
        //returns the next available card if possible
        if(available.get(0) != null){
            top = available.get(0);
            available.remove(0);
            return top;
        }
        //the available pile is empty; check the discard pile
        else{
            //check if the discard pile is not empty
            if(discardPile.size() != 0) {
                available = discardPile;
                discardPile = new ArrayList<>();
                shuffle();
                top = available.get(0);
                available.remove(0);
                return top;
            }
            else throw new Exception();
        }
    }
}
