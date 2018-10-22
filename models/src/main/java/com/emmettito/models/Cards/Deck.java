package com.emmettito.models.Cards;

public interface Deck {
    void shuffle();
    void addCardToBottom(Card cardToAdd);
    Card drawCard() throws Exception;
}
