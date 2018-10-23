package com.emmettito.models.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DestinationCardDeck implements Deck {
    private List<DestinationCard> availableCards;
    private List<DestinationCard> discardPile;

    public DestinationCardDeck(){
        availableCards = new ArrayList<>();
        discardPile = new ArrayList<>();

        for(int i = 0; i <= 30; i++){
            Random r = new Random();
            int randomPointValue = r.nextInt((23 - 4) + 1) + 23;
            availableCards.add(new DestinationCard("Provo", "Las Vegas", randomPointValue)); // TODO: Add cities and point values
        }

        shuffle();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(availableCards);
        Collections.shuffle(discardPile);
    }

    public void setAvailableCards(List<DestinationCard> availableCards) {
        this.availableCards = availableCards;
    }

    public void setDiscardPile(List<DestinationCard> discardPile) {
        this.discardPile = discardPile;
    }

    public List<DestinationCard> getAvailableCards() {
        return availableCards;
    }

    public List<DestinationCard> getDiscardPile() {
        return discardPile;
    }

}
