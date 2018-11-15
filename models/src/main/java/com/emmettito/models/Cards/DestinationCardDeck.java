package com.emmettito.models.Cards;

import com.emmettito.models.HardCoded.HardCodedData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DestinationCardDeck implements Deck {
    private List<DestinationCard> availableCards;
    private List<DestinationCard> discardPile;

    public DestinationCardDeck(){
        availableCards = new HardCodedData().getDestinationCards();
        discardPile = new ArrayList<>();

        shuffle();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(availableCards);
        Collections.shuffle(discardPile);
    }

    public void addCards(List<DestinationCard> addCards) {
        for (int i = 0; i < addCards.size(); i++) {
            availableCards.add(addCards.get(i));
        }
    }

    public void setAvailableCards(List<DestinationCard> availableCards) {
        this.availableCards = availableCards;
    }

    public void setDiscardPile(List<DestinationCard> discardPile) {
        this.discardPile = discardPile;
    }

    public List<DestinationCard> drawnThreeCards() {
        if (availableCards.size() < 3) {
            return null;
        }
        else {
            List<DestinationCard> drawnCards =  new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                DestinationCard drawn = availableCards.get(0);
                availableCards.remove(0);
                drawnCards.add(drawn);
            }
            return drawnCards;
        }
    }

    public List<DestinationCard> getAvailableCards() {
        return availableCards;
    }

    public List<DestinationCard> getDiscardPile() {
        return discardPile;
    }

}
