package com.emmettito.tickettoride.presenters;

import com.emmettito.models.CommandModels.GameCommands.GetCommandsRequest;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.communication.Poller;
import com.emmettito.tickettoride.facades.ServerFacade;
import com.emmettito.tickettoride.views.GameActivity.GameHistoryActivity;
import com.google.gson.Gson;

import java.util.Observable;
import java.util.Observer;

public class GameHistoryPresenter implements Observer {
    private Poller poller;
    private ServerFacade facade = null;
    private GameHistoryActivity view;
    private Client client = Client.getInstance();

    /**
     *
     * We are going to have to bulletproof this later
     *
     */

    String url = "http://"+client.getIpAddress()+":8080/game/getcommands";

    /**
     *
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     *
     */

    public GameHistoryPresenter(GameHistoryActivity view) {
        this.view = view;
        facade = ServerFacade.getInstance(client.getIpAddress(), "8080");
    }

    @Override
    public void update(Observable obj, Object arg) {
        String string = (String) arg;

        System.out.println(string);

        view.update(arg);
    }

    /*public ChatResult sendMessage(String message) {
        ChatRequest request = new ChatRequest();
        request.setGameName(client.getGameName());
        request.setPlayerName(client.getUser());
        request.setMessage(message);

        return facade.sendChatMessage(request, client.getIpAddress(), "8080");
    }*/

    public void startPoller() {
        GetCommandsRequest request = new GetCommandsRequest(client.getGameName(), 0);

        String requestString = new Gson().toJson(request);

        poller = new Poller(url, requestString);

        poller.addObserver(this);

        poller.start(3);
    }

    public interface gameHistoryView {}

    public void shutDownPoller() {
        poller.shutdown();
    }
}
