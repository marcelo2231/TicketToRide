package com.emmettito.tickettoride.presenters;

import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.JoinGameRequest;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.communication.Poller;
import com.emmettito.tickettoride.facades.ServerFacade;
import com.emmettito.tickettoride.views.LobbyActivity.GameListFragment;

import java.util.Observable;
import java.util.Observer;

public class LobbyPresenter implements Observer {

    private Client client = Client.getInstance();
    //private List<Observer> observers;
    private Poller poller;
    private ServerFacade facade = null;
    private GameListFragment view;

    /**
     *
     * We are going to have to bulletproof this later
     *
     */

    String url = "http://" + client.getIpAddress() + ":8080/gamelobby/getgames";

    /**
     *
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     *
     */

    public LobbyPresenter(GameListFragment view) {
        this.view = view;
        facade = ServerFacade.getInstance(client.getIpAddress(), "8080");
    }

    public void update(Observable obj, Object arg) {
        //setChanged();
        //notifyObservers(arg);

        view.update(arg);
    }

    public GameLobbyResult createNewGame(String gameName, String username) {
        CreateGameRequest request = new CreateGameRequest();
        request.setGameName(gameName);
        request.setUsername(username);

        //GameLobbyProxy proxy = new GameLobbyProxy(client.getIpAddress(), "8080");

        //return proxy.createGame(request);

        return facade.createNewGame(request);
    }

    public GameLobbyResult joinGame(String gameName, String username) {
        JoinGameRequest request = new JoinGameRequest();
        request.setGameName(gameName);
        request.setUsername(username);

        //GameLobbyProxy proxy = new GameLobbyProxy(client.getIpAddress(), "8080");

        //return proxy.joinGame(request);

        return facade.joinGame(request);
    }

    public interface lobbyView {
        void createNewGame(String gameName, String username);

        void joinGame(String gameName, String username);
    }

    public void startPoller() {
        poller = new Poller(url);

        poller.addObserver(this);

        poller.start(3);
    }

    public void shutDownPoller() {
        poller.shutdown();
    }
}
