package com.emmettito.tickettoride.presenters;

import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GetPlayersResult;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.facades.ServerFacade;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GamePresenter implements Observer {

    private Client client = Client.getInstance();

    private ServerFacade facade = null;
    @Override
    public void update(Observable observable, Object o) {

    }
    public ArrayList<Player> getPlayers(){
        facade = ServerFacade.getInstance();
        GetPlayersRequest request = new GetPlayersRequest(client.getGameName());
        GetPlayersResult result = facade.getPlayers(request, "10.0.2.2", "8080");
        return result.getData();
    }
}
