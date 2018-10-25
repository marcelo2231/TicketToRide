package com.emmettito.tickettoride.presenters;

import android.util.Log;
import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.communication.Poller;
import com.emmettito.tickettoride.views.GameRoomActivity.GameRoomActivity;
import com.google.gson.Gson;
import java.util.Observable;
import java.util.Observer;


public class GameRoomPresenter implements Observer {

    private Poller poller;
    private Client client;
    private Gson gson;
    private GameRoomActivity activity;

    public GameRoomPresenter() {
        activity = null;
        client = Client.getInstance();
        gson = new Gson();
    }

    public void update(Observable obj, Object arg) {
        activity.update(arg);
    }

    public void startPoller(String url, GameRoomActivity activity) {

        this.activity = activity;

        GetPlayersRequest request = new GetPlayersRequest(client.getGameName());
        String requestBody = gson.toJson(request);

        Log.w("startPoller", requestBody);

        poller = new Poller(url, requestBody);
        poller.addObserver(this);
        poller.start(3);
    }

    public void shutDownPoller() {
        poller.shutdown();
    }

    public interface GameRoomView {

        void startGame();

        void leaveGame();

    }
}