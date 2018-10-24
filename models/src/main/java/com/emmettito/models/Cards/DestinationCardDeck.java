package com.emmettito.models.Cards;

import java.util.Collections;
import java.util.List;

public class DestinationCardDeck implements Deck {
    private List<DestinationCard> availableCards;


    @Override
    public void shuffle() {
        Collections.shuffle(availableCards);
    }

    @Override
    public void addCardToBottom(Card cardToAdd) {
        DestinationCard card = (DestinationCard) cardToAdd;
        availableCards.add(card);
    }

    public void setAvailableCards(List<DestinationCard> availableCards) {
        this.availableCards = availableCards;
    }

    public List<DestinationCard> getAvailableCards() {
        return availableCards;
    }

    @Override
    public DestinationCard drawCard()throws Exception{
        //returns the first available card
        if(availableCards.get(0) != null){
            DestinationCard top = availableCards.get(0);
            availableCards.remove(0);
            return top;
        }
        else{
            //can't draw since there are no cards: throw an exception
            return null;
        }
    }
}
