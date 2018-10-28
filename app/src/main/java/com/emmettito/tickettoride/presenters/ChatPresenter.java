package com.emmettito.tickettoride.presenters;

import com.emmettito.models.CommandModels.GameCommands.ChatRequest;
import com.emmettito.models.Results.ChatResult;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.communication.Poller;
import com.emmettito.tickettoride.facades.ServerFacade;
import com.emmettito.tickettoride.views.GameActivity.ChatActivity;
import com.google.gson.Gson;

import java.util.Observable;
import java.util.Observer;

public class ChatPresenter implements Observer {
    private Poller poller;
    private ServerFacade facade = null;
    private ChatActivity view;
    private Client client = Client.getInstance();

    /**
     *
     * We are going to have to bulletproof this later
     *
     */

    String url = "http://10.0.2.2:8080/game/getchat";

    /**
     *
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     *
     */

    public ChatPresenter(ChatActivity view) {
        this.view = view;
        facade = ServerFacade.getInstance();
    }

    @Override
    public void update(Observable obj, Object arg) {
        String string = (String) arg;

        System.out.println(string);

        view.update(arg);
    }

    public ChatResult sendMessage(String message) {
        ChatRequest request = new ChatRequest();
        request.setGameName(client.getGameName());
        request.setPlayerName(client.getUser());
        request.setMessage(message);

        return facade.sendChatMessage(request, "10.0.2.2", "8080");
    }

    public void startPoller() {
        ChatRequest request = new ChatRequest();
        request.setGameName(client.getGameName());

        String requestString = new Gson().toJson(request);

        poller = new Poller(url, requestString);

        poller.addObserver(this);

        poller.start(3);
    }

    public interface chatView {
        void sendMessage(String message);
    }

    public void shutDownPoller() {
        poller.shutdown();
    }
}
