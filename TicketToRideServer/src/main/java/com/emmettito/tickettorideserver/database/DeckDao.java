package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.DestinationCardDeck;
import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainCardDeck;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import java.util.ArrayList;
import java.util.List;

public class DeckDao {
    /** Variables **/
    private static Database dbInstance = Database.getInstance();
    GameLobbyDao gameLobbyDao = new GameLobbyDao();
    GameDao gameDao = new GameDao();

    /** Destination Card Deck **/
    public DestinationCardDeck getDestCardDeck(String gameName) throws Exception{
        Game game = gameLobbyDao.getActiveGame(gameName);
        if (game == null) { throw new Exception("Invalid game name."); }
        DestinationCardDeck deck = game.getDestinationCardDeck();
        if (deck == null) { throw new Exception("Destination card Deck is null."); }
        return deck;
    }

    public ArrayList<DestinationCard> getPlayerDestCardDeck(String gameName, String playerName) throws Exception{
        Player player = gameDao.getPlayer(gameName, playerName);
        if (player == null) { throw new Exception("Unable to find player on this game."); }
        ArrayList<DestinationCard> deck = player.getDestinationCards();
        if (deck == null) { throw new Exception("Deck is null."); }
        return deck;
    }

    public void shuffleDestCard(String gameName) throws Exception{
        DestinationCardDeck deck = getDestCardDeck(gameName);
        deck.shuffle();
    }

    public boolean addDestCardToBottomDeck(String gameName, DestinationCard card) throws Exception{
        DestinationCardDeck deck = getDestCardDeck(gameName);
        return deck.getAvailableCards().add(card);
    }

    public DestinationCard removeTopDestCardFromDeck(String gameName) throws Exception{
        DestinationCardDeck deck = getDestCardDeck(gameName);
        List<DestinationCard> availableCards = deck.getAvailableCards();

        if(availableCards.size() == 0){
            deck.setAvailableCards(deck.getDiscardPile());
            deck.setDiscardPile(new ArrayList<DestinationCard>());
            deck.shuffle();
            availableCards = deck.getAvailableCards();
            if(availableCards.size() == 0) {
                throw new Exception("There is no destination card left on deck.");
            }
        }

        DestinationCard top = availableCards.get(0);
        availableCards.remove(0);
        return top;
    }

    public boolean addDestCardToPlayer(String gameName, String playerName, DestinationCard card) throws Exception{
        ArrayList<DestinationCard> deck = getPlayerDestCardDeck(gameName , playerName);
        return deck.add(card);
    }

    public boolean removeDestCardFromPlayer(String gameName, String playerName, int cardID) throws Exception{
        ArrayList<DestinationCard> deck = getPlayerDestCardDeck(gameName, playerName);
        Game game = gameLobbyDao.getGame(gameName);
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
        Game game = gameLobbyDao.getActiveGame(gameName);
        if (game == null) { throw new Exception("Invalid game name."); }
        TrainCardDeck deck = game.getTrainCardDeck();
        if (deck == null) { throw new Exception("Train card Deck is null."); }
        return deck;
    }

    public ArrayList<TrainCard> getPlayerTrainCardDeck(String gameName, String playerName) throws Exception{
        Player player = gameDao.getPlayer(gameName, playerName);
        if (player == null) { throw new Exception("Unable to find player on this game."); }
        ArrayList<TrainCard> deck = player.getTrainCards();
        if (deck == null) { throw new Exception("Deck is null."); }
        return deck;
    }

    public void shuffleTrainCard(String gameName) throws Exception{
        TrainCardDeck deck = getTrainCardDeck(gameName);
        deck.shuffle();
    }

    public boolean addTrainCardToBottomDeck(String gameName, TrainCard card) throws Exception{
        TrainCardDeck deck = getTrainCardDeck(gameName);
        return deck.getAvailable().add(card);
    }

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
        return top;
    }

    public TrainCard removeFaceUpTrainCardFromDeck(String gameName, int cardIndex) throws Exception{
        TrainCardDeck deck = getTrainCardDeck(gameName);
        List<TrainCard> faceUpCards = deck.getFaceUpCards();

        if(faceUpCards.size() < cardIndex + 1){
            throw new Exception("Invalid index(" + cardIndex + "), there are only " + faceUpCards.size() + " cards faced up. Index must be between 0 and " +
                    (faceUpCards.size() - 1) + ".");
        }

        // Remove card from top
        TrainCard result = faceUpCards.get(cardIndex);
        faceUpCards.remove(cardIndex);

        try{
            TrainCard card = removeTopTrainCardFromDeck(gameName);
            faceUpCards.add(card);
        }catch(Exception e){
            // There is no card left on deck, and discard pile, do nothing.
        }

        return result;
    }

    public boolean addTrainCardToPlayer(String gameName, String playerName, TrainCard card) throws Exception{
        ArrayList<TrainCard> deck = getPlayerTrainCardDeck(gameName , playerName);
        return deck.add(card);
    }

    public boolean removeTrainCardFromPlayer(String gameName, String playerName, int cardID) throws Exception{
        ArrayList<TrainCard> deck = getPlayerTrainCardDeck(gameName, playerName);
        Game game = gameLobbyDao.getGame(gameName);
        for (TrainCard c : deck){
            if (c.getCardID() == cardID){
                game.getTrainCardDeck().getDiscardPile().add(c);
                return deck.remove(c);
            }
        }
        throw new Exception("Train card is not in deck");
    }
}
