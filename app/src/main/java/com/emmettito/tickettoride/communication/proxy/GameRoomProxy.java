package com.emmettito.tickettoride.communication.proxy;

import android.util.Log;

import com.emmettito.models.CommandModels.GameCommands.QuitGameRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.StartGameRequest;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.models.Results.GetPlayersResult;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.communication.ClientCommunicator;
import com.google.gson.Gson;

public class GameRoomProxy {

    private ClientCommunicator clientCommunicator;
    private Client client;
    private Gson gson;

    private String serverHost = "10.0.2.2";
    private String serverPort = "8080";

    public GameRoomProxy()  {
        client = Client.getInstance();
        gson = new Gson();
    }

    public boolean leaveGame() {

        clientCommunicator = new ClientCommunicator();

        Client clientInstance = Client.getInstance();
        serverHost = clientInstance.getIpAddress();
        String url = "http://" + serverHost + ":" + serverPort + "/game/quitgame";

        QuitGameRequest request = new QuitGameRequest();
        request.setGameName(client.getGameName());
        request.setPlayerName(client.getUser());

        String responseBody;
        String requestBody = gson.toJson(request);

        try {
            responseBody = clientCommunicator.execute(url, "POST", requestBody).get();
        } catch (Exception e) {
            return false;
        }

        if (responseBody.contains("Error")) { //Server is down
            return false;
        }

        Result result = gson.fromJson(responseBody, Result.class);

        if (result.getSuccess()) {
            client.deleteGameName();
            return true;
        }
        else {
            return false;
        }
    }

    public GameLobbyResult startGame() {

        clientCommunicator = new ClientCommunicator();


        Client clientInstance = Client.getInstance();
        serverHost = clientInstance.getIpAddress();
        String url = "http://" + serverHost + ":" + serverPort + "/gamelobby/startgame";

        StartGameRequest request = new StartGameRequest();
        request.setGameName(client.getGameName());
        request.setPlayerName(client.getUser());

        String responseBody;
        String requestBody = gson.toJson(request);

        try {
            responseBody = clientCommunicator.execute(url, "POST", requestBody).get();
        } catch (Exception e) {
            return new GameLobbyResult(false, "Error: Could not start the game.");
        }

        if (responseBody.contains("Error")) { //Server is down
            GameLobbyResult result = new GameLobbyResult();
            result.setSuccess(false);
            result.setMessage("Error: could not connect to server.");

            return result;
        }

        Log.w("startGameResult", responseBody);
        GameLobbyResult result = gson.fromJson(responseBody, GameLobbyResult.class);
        return result;
    }

    public GetPlayersResult getPlayers(GetPlayersRequest request) {

        clientCommunicator = new ClientCommunicator();


        Client clientInstance = Client.getInstance();
        serverHost = clientInstance.getIpAddress();
        String url = "http://" + serverHost + ":" + serverPort + "/gamelobby/creategame"; // TODO: FIX URL

        String responseBody;
        String requestBody = gson.toJson(request);


        try {
            responseBody = clientCommunicator.execute(url, "POST", requestBody).get();
        } catch (Exception e) {
            responseBody = null;
        }

        if (responseBody == null) {
            /*GetPlayersResult getPlayersResult = new GetPlayersResult();
            getPlayersResult.setMessage("Error: Could not connect to the server.");*/
            return null;
        }

        return gson.fromJson(responseBody, GetPlayersResult.class);
    }
}
