package com.emmettito.tickettoride.presenters;

import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.JoinGameRequest;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettoride.communication.Poller;
import com.emmettito.tickettoride.communication.proxy.GameLobbyProxy;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class GameRoomPresenter extends Observable {

    private Poller poller;

    public GameRoomPresenter() {}

    public void startPoller(String url, Observer o) {
        poller = new Poller(url);
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