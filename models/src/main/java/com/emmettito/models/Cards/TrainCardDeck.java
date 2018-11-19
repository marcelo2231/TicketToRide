package com.emmettito.models.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainCardDeck implements Deck {
    private List<TrainCard> available;
    private List<TrainCard> faceUpCards;
    private List<TrainCard> discardPile;
    private int numFaceUpWilds = 0;

    public TrainCardDeck(){
        available = new ArrayList<>();
        discardPile = new ArrayList<>();
        faceUpCards = new ArrayList<>();

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
            //record the number of faceUp wild cards
            if(available.get(i).getColor() == (TrainColor.Wild)){
                numFaceUpWilds++;
            }
            faceUpCards.add(available.get(i));
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

    public List<TrainCard> getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(List<TrainCard> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }

    public void setNumFaceUpWilds(int num){
        this.numFaceUpWilds = num;
    }

    public int getNumFaceUpWilds(){
        return numFaceUpWilds;
    }
}
