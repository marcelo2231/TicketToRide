package com.emmettito.tickettoride.presenters;

import android.widget.Button;

import com.emmettito.models.Cards.DestinationCardDeck;
import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainCardDeck;
import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.CommandModels.GameCommands.GetCommandsRequest;
import com.emmettito.models.CommandModels.GameCommands.GetGameRequest;
import com.emmettito.models.CommandModels.GameCommands.PlayerTurnRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GetCommandsResult;
import com.emmettito.models.Results.GetGameResult;
import com.emmettito.models.Results.GetPlayersResult;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.facades.ServerFacade;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GamePresenter implements Observer {

    private Client client = Client.getInstance();

    private ServerFacade facade = null;

    private GameActivity game;

    public GamePresenter(GameActivity game) {
        this.game = game;
    }

    @Override
    public void update(Observable observable, Object o) {
        game.updatePlayerDisplay();

        if(o.getClass().equals(TrainCardDeck.class)) {
            game.updateCardDeck();
        }

        if (o.getClass().equals(DestinationCardDeck.class)) {
            game.updateDestinationCardDeck();
        }
    }

    public void addObserver() {
        client.getGame().addObserver(this);

        client.addObserver(this);
    }

    public ArrayList<Player> getPlayers(){
        facade = ServerFacade.getInstance("10.0.2.2", "8080");
        GetPlayersRequest request = new GetPlayersRequest(client.getGameName());
        GetPlayersResult result = facade.getPlayers(request);
        return result.getData();
    }

    public Game getGame(){
        facade = ServerFacade.getInstance();
        GetGameRequest request = new GetGameRequest();
        request.setGameName(client.getGameName());
        GetGameResult result = facade.getGame(request, "10.0.2.2", "8080");
        return result.getData();
    }

    public ArrayList<Command> getCommands(int atIndex){
        facade = ServerFacade.getInstance("10.0.2.2", "8080");
        GetCommandsRequest request = new GetCommandsRequest(client.getGameName(), atIndex);
        GetCommandsResult result = facade.getCommands(request);
        return result.getData();
    }

    public int endPlayerTurn(Game game){
        facade = ServerFacade.getInstance("10.0.2.2", "8080");
        PlayerTurnRequest request = new PlayerTurnRequest();
        //gets the name of the player whose turn it is
        request.setPlayerName(game.getPlayers().get(game.getPlayerTurnIndex()).getPlayerName());
        request.setGameName(game.getGameName());
        request.setGameIndex(game.getPlayerTurnIndex());
        Result result = facade.endTurn(request, "10.0.2.2", "8080");
        int newIndex = -1;
        try{
            newIndex = (int) result.getData();
        }catch (Exception e){
            //testing that it works right
            if(game.getPlayerTurnIndex()+1 >= game.getPlayers().size()){
                return 0;
            }
            return game.getPlayerTurnIndex()+1;

            //if something went wrong, return the original index
            //return game.getPlayerTurnIndex();
        }
        return newIndex;
    }

    public void drawFaceUpTrainCard(GameActivity gameActivity, Game game, TrainCard oldCard, int trainCardIndex, Button trainButton){
        game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards().add(oldCard);
        game.getPlayers().get(game.getPlayerTurnIndex()).setTrainCards(game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards());
        gameActivity.addTrainCardToPlayer(oldCard);

        if(gameActivity.checkTrainCardDeck()){
            TrainCard newCard = game.getTrainCardDeck().getAvailable().remove(0);
            game.getTrainCardDeck().getFaceUpCards().add(trainCardIndex, newCard);
            trainButton.setBackground(gameActivity.updateFaceUpCard(newCard));
            gameActivity.getDeckTrainCards().setText(String.valueOf(game.getTrainCardDeck().getSizeAvailable()));
        }
        else{
            trainButton.setBackgroundColor(0x00);
            trainButton.setBackgroundResource(android.R.drawable.btn_default);
            game.getTrainCardDeck().getFaceUpCards().add(trainCardIndex, null);
            trainButton.setEnabled(false);
        }
    }

}
