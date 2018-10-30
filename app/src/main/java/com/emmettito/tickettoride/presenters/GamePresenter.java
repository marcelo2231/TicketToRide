package com.emmettito.tickettoride.presenters;

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

    public GamePresenter(GameActivity game) {
        this.game = game;

        Game.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        game.updatePlayerDisplay();
    }


    public ArrayList<Player> getPlayers(){
        facade = ServerFacade.getInstance();
        GetPlayersRequest request = new GetPlayersRequest(client.getGameName());
        GetPlayersResult result = facade.getPlayers(request, "10.0.2.2", "8080");
        return result.getData();
    }
}
