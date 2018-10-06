package com.emmettito.tickettoride.presenters;

import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.JoinGameRequest;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettoride.communication.Poller;
import com.emmettito.tickettoride.communication.proxy.GameLobbyProxy;

import java.util.Observable;
import java.util.Observer;

public class LobbyPresenter extends Observable implements Observer {

    //private List<Observer> observers;
    private Poller poller;

    /**
     *
     * We are going to have to bulletproof this later
     *
     */

    String url = "http://10.0.2.2:8080/gamelobby/getgames";

    /**
     *
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     *
     */

    public LobbyPresenter() {}

    public void update(Observable obj, Object arg) {
        setChanged();
        notifyObservers(arg);

        //System.out.println("This is a place where I did get");
    }

    public GameLobbyResult createNewGame(String gameName, String username, String authToken) {
        CreateGameRequest request = new CreateGameRequest();
        request.setGameName(gameName);
        request.setUsername(username);

        GameLobbyProxy proxy = new GameLobbyProxy("10.0.2.2", "8080");

        return proxy.createGame(request, authToken);
    }

    public GameLobbyResult joinGame(String gameName, String username, String authToken) {
        JoinGameRequest request = new JoinGameRequest();
        request.setGameName(gameName);
        request.setUsername(username);

        GameLobbyProxy proxy = new GameLobbyProxy("10.0.2.2", "8080");

        return proxy.joinGame(request, authToken);
    }

    public interface lobbyView {
        void createNewGame(String gameName, String username, String authToken);

        void joinGame(String gameName, String username, String authToken);
    }

    public void startPoller() {
        poller = new Poller(url);

        poller.addObserver(this);

        poller.start(3);
    }
}
