package com.emmettito.tickettoride.presenters;

import android.util.Log;

import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.JoinGameRequest;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.communication.Poller;
import com.emmettito.tickettoride.communication.proxy.GameLobbyProxy;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class GameRoomPresenter extends Observable {

    private Poller poller;
    private Client client;
    private Gson gson;

    public GameRoomPresenter() {
        client = Client.getInstance();
        gson = new Gson();
    }

    public void startPoller(String url, Observer o) {

        GetPlayersRequest request = new GetPlayersRequest(client.getGameName());
        String requestBody = gson.toJson(request);

        Log.w("startPoller", requestBody);

        poller = new Poller(url, requestBody);
        poller.addObserver(o);
        poller.start(3);
    }

    public void shutDownPoller() {
        poller.shutdown();
    }

    public interface GameRoomView extends Observer{

        /*public int getPlayers();*/

        ArrayList<Player> getPlayers();

        void startGame();

        void leaveGame();

        void cancelGame();
    }
}