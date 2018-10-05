package com.emmettito.tickettoride.presenters;

import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettoride.communication.proxy.GameLobbyProxy;

import java.util.Observable;

public class LobbyPresenter extends Observable {

    //private List<Observer> observers;

    public LobbyPresenter() {
        //observers = new ArrayList<>();
    }

    /*@Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }*/

    public GameLobbyResult createNewGame(String gameName, String username, String authToken) {
        CreateGameRequest request = new CreateGameRequest();
        request.setGameName(gameName);
        request.setUsername(username);

        GameLobbyProxy proxy = new GameLobbyProxy("10.0.2.2", "8080");

        return proxy.createGame(request, authToken);
    }

    public interface lobbyView {
        void createNewGame(String gameName, String username, String authToken);

        void joinGame(String gameName, String username, String authToken);
    }
}
