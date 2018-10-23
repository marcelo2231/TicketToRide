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
        // Add two more locomotive, since we need 14 of those
        available.add(new TrainCard(TrainColor.Wild));
        available.add(new TrainCard(TrainColor.Wild));

        shuffle();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(available);
        Collections.shuffle(discardPile);
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

}
