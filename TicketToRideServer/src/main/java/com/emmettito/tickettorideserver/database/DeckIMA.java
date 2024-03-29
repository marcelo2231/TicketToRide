package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.DestinationCardDeck;
import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainCardDeck;
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import java.util.ArrayList;
import java.util.List;

public class DeckIMA {
    /** Variables **/
    GameIMA mGameIMA = new GameIMA();

    private int numShuffles = 0;

    /** Destination Card Deck **/
    public DestinationCardDeck getDestCardDeck(String gameName) throws Exception{
        Game game = mGameIMA.getGame(gameName);
        if (game == null) {
            throw new Exception("Invalid game name.");
        }
        DestinationCardDeck deck = game.getDestinationCardDeck();
        if (deck == null) {
            System.out.println("No deck!");
            throw new Exception("Destination card Deck is null.");
        }
        return deck;
    }

    public ArrayList<DestinationCard> getPlayerDestCardDeck(String gameName, String playerName) throws Exception{
        Player player = mGameIMA.getPlayer(gameName, playerName);
        if (player == null) {
            throw new Exception("Unable to find player on this game.");
        }
        ArrayList<DestinationCard> deck = player.getDestinationCards();
        if (deck == null) {
            throw new Exception("Deck is null.");
        }
        return deck;
    }

    /*public boolean addDestCardToBottomDeck(String gameName, DestinationCard card) throws Exception{
        DestinationCardDeck deck = getDestCardDeck(gameName);
        return deck.getAvailableCards().add(card);
    }*/

    public DestinationCard removeTopDestCardFromDeck(String gameName) throws Exception{
        DestinationCardDeck deck = getDestCardDeck(gameName);
        List<DestinationCard> availableCards = deck.getAvailableCards();

        System.out.println("Got");

        if(availableCards.size() == 0){
            deck.setAvailableCards(deck.getDiscardPile());
            deck.setDiscardPile(new ArrayList<DestinationCard>());
            deck.shuffle();
            availableCards = deck.getAvailableCards();
            if(availableCards.size() == 0) {
                System.out.println("Got here");
                throw new Exception("There is no destination card left on deck.");
            }
        }

        DestinationCard top = availableCards.get(0);
        availableCards.remove(0);

        System.out.println("There");

        Game game = mGameIMA.getGame(gameName);
        deck.setAvailableCards(availableCards);
        game.setDestinationCardDeck(deck);
        mGameIMA.setGame(game);

        return top;
    }

    public boolean addDestCardToPlayer(String gameName, String playerName, DestinationCard card) throws Exception{
        ArrayList<DestinationCard> deck = getPlayerDestCardDeck(gameName , playerName);
        return deck.add(card);
    }

    public boolean removeDestCardFromPlayer(String gameName, String playerName, int cardID) throws Exception{
        ArrayList<DestinationCard> deck = getPlayerDestCardDeck(gameName, playerName);
        Game game = mGameIMA.getGame(gameName);
        for (DestinationCard c : deck){
            if (c.getCardID() == cardID){
                game.getDestinationCardDeck().getDiscardPile().add(c);
                return deck.remove(c);
            }
        }
        throw new Exception("Destination card is not in deck");
    }

    /** Train Card Deck **/
    public TrainCardDeck getTrainCardDeck(String gameName) throws Exception{
        Game game = mGameIMA.getGame(gameName);
        if (game == null) {
            throw new Exception("Invalid game name.");
        }
        TrainCardDeck deck = game.getTrainCardDeck();
        if (deck == null) {
            throw new Exception("Train card Deck is null.");
        }
        return deck;
    }

    public ArrayList<TrainCard> getPlayerTrainCardDeck(String gameName, String playerName) throws Exception{
        Player player = mGameIMA.getPlayer(gameName, playerName);
        if (player == null) {
            throw new Exception("Unable to find player on this game.");
        }
        ArrayList<TrainCard> deck = player.getTrainCards();
        if (deck == null) {
            throw new Exception("Deck is null.");
        }
        return deck;
    }

    /*public void shuffleTrainCard(String gameName) throws Exception{
        TrainCardDeck deck = getTrainCardDeck(gameName);
        deck.shuffle();
    }*/

    /*public boolean addTrainCardToBottomDeck(String gameName, TrainCard card) throws Exception{
        TrainCardDeck deck = getTrainCardDeck(gameName);
        return deck.getAvailable().add(card);
    }*/

    public TrainCard removeTopTrainCardFromDeck(String gameName) throws Exception{
        TrainCardDeck deck = getTrainCardDeck(gameName);
        List<TrainCard> availableCards = deck.getAvailable();
        List<TrainCard> discardPile = deck.getDiscardPile();
        TrainCard top;

        if(availableCards.size() == 0){
            if(discardPile.size() != 0) {
                availableCards = discardPile;
                discardPile = new ArrayList<>();
                deck.shuffle();
            }
            else throw new Exception("There is no train card left on deck."); // Exceptions are handled by the server side
        }
        // Remove card from top
        top = availableCards.get(0);
        availableCards.remove(0);


        Game game = mGameIMA.getGame(gameName);
        deck.setAvailable(availableCards);
        game.setTrainCardDeck(deck);
        mGameIMA.setGame(game);

        return top;
    }

    public TrainCard removeFaceUpTrainCardFromDeck(String gameName, int cardIndex) throws Exception {
        TrainCardDeck deck = getTrainCardDeck(gameName);
        List<TrainCard> faceUpCards = deck.getFaceUpCards();

        // Remove card from top
        TrainCard result = faceUpCards.get(cardIndex);
        //faceUpCards.remove(cardIndex);
        faceUpCards.set(cardIndex, null);

        try {
            TrainCard card = removeTopTrainCardFromDeck(gameName);
            faceUpCards.set(cardIndex, card);
        } catch (Exception e) {
            // There is no card left on deck, and discard pile, do nothing.
        }

        return result;
    }
    

    public boolean addTrainCardToPlayer(String gameName, String playerName, TrainCard card) throws Exception{
        ArrayList<TrainCard> deck = getPlayerTrainCardDeck(gameName , playerName);
        deck.add(card);

        Player player = mGameIMA.getPlayer(gameName, playerName);
        player.setTrainCards(deck);
        Game game = mGameIMA.getGame(gameName);
        game.setOnePlayer(player);
        mGameIMA.setGame(game);

        return true;
    }

    public boolean removeTrainCardFromPlayer(String gameName, String playerName, int cardID) throws Exception{
        ArrayList<TrainCard> deck = getPlayerTrainCardDeck(gameName, playerName);
        Game game = mGameIMA.getGame(gameName);
        for (TrainCard c : deck){
            if (c.getCardID() == cardID){
                game.getTrainCardDeck().getDiscardPile().add(c);
                return deck.remove(c);
            }
        }
        throw new Exception("Train card is not in deck");
    }

    public int getNumShuffles(){ return numShuffles; }

    //when there are 3 or more wild cards faceUp, the 5 faceUp cards are discarded, then the top 5 cards are placed faceUp
    public boolean reShuffleFaceUpCards(String gameName) throws Exception{
        int NUM_FACEUP_CARDS = 5;
        TrainCardDeck deck = getTrainCardDeck(gameName);

        //discard non-null cards
        for(int i = 0; i < NUM_FACEUP_CARDS; i++){
            TrainCard oldCard = deck.getFaceUpCards().get(i);
            if(oldCard != null) {
                deck.getDiscardPile().add(oldCard);
            }
        }
        ///reset the faceUp array and wild counter
        ArrayList<TrainCard> newFaceUps = new ArrayList<>();
        deck.setFaceUpCards(newFaceUps);
        deck.setNumFaceUpWilds(0);

        //take the top 5 cards and place them faceUp
        int numWilds = 0;
        for(int i = 0; i < NUM_FACEUP_CARDS; i++){
            if (!deck.getAvailable().isEmpty()) {
                TrainCard newCard = deck.getAvailable().remove(0);
                deck.getFaceUpCards().add(newCard);
                if(newCard.getColor() == TrainColor.Wild){
                    numWilds = deck.getNumFaceUpWilds();
                    deck.setNumFaceUpWilds(++numWilds);
                }
            }
        }
        //only shuffle a maximum of 6 times when there's wilds again after shuffling
        numShuffles++;
        if(deck.getNumFaceUpWilds() >= 3){
            if(numShuffles < 5) {
                reShuffleFaceUpCards(gameName);
            }
        }

        Game game = mGameIMA.getGame(gameName);
        game.setTrainCardDeck(deck);
        mGameIMA.setGame(game);

        return true;
    }
}
