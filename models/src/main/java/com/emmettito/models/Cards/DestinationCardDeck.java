package com.emmettito.models.Cards;

import java.util.List;

public class DestinationCardDeck implements Deck {
    private List<DestinationCard> availableCards;
    private List<DestinationCard> discardPile;

    @Override
    public void shuffle() {

    }

    @Override
    public void addCardToBottom(Card cardToAdd) {

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

    @Override
    public DestinationCard drawCard(){
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
