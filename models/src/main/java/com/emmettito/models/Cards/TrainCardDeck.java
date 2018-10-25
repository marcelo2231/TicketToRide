package com.emmettito.models.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainCardDeck implements Deck {
    private List<TrainCard> available;
    private List<TrainCard> faceUp;
    private List<TrainCard> discardPile;

    public TrainCardDeck(){
        available = new ArrayList<>();
        discardPile = new ArrayList<>();
        faceUp = new ArrayList<>();

        for(int i = 0; i < 12; i++){
            available.add(new TrainCard(TrainColor.Red, (i*9)+1));
            available.add(new TrainCard(TrainColor.Orange,  (i*9)+2));
            available.add(new TrainCard(TrainColor.Yellow,  (i*9)+3));
            available.add(new TrainCard(TrainColor.Green,  (i*9)+4));
            available.add(new TrainCard(TrainColor.Blue,  (i*9)+5));
            available.add(new TrainCard(TrainColor.Black,  (i*9)+6));
            available.add(new TrainCard(TrainColor.White,  (i*9)+7));
            available.add(new TrainCard(TrainColor.Pink,  (i*9)+8));
            available.add(new TrainCard(TrainColor.Wild,  (i*9)+9));
        }
        // Add two more locomotive, since we need 14 of those
        available.add(new TrainCard(TrainColor.Wild, 109));
        available.add(new TrainCard(TrainColor.Wild, 110));

        shuffle();

        //take the first 5 cards and place them face-up
        for(int i = 0; i < 5; i++){
            faceUp.add(available.get(i));
            available.remove(i);
        }
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

    public List<TrainCard> getFaceUp() {
        return faceUp;
    }

    public void setFaceUp(List<TrainCard> faceUp) {
        this.faceUp = faceUp;
    }
}
