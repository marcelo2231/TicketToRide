package com.emmettito.tickettoride.presenters;

import com.emmettito.models.Cards.DestinationCardDeck;
import com.emmettito.models.Cards.TrainCardDeck;
import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GetPlayersResult;
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

    private Game gameObject;

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

    public void addGame(Game gameObject) {
        this.gameObject = gameObject;

        gameObject.addObserver(this);
    }

    public ArrayList<Player> getPlayers(){
        facade = ServerFacade.getInstance();
        GetPlayersRequest request = new GetPlayersRequest(client.getGameName());
        GetPlayersResult result = facade.getPlayers(request, "10.0.2.2", "8080");
        return result.getData();
    }
}
