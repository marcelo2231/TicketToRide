package com.emmettito.tickettoride.communication.proxy;

import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.JoinGameRequest;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettoride.communication.ClientCommunicator;
import com.google.gson.Gson;

public class GameLobbyProxy {
    private ClientCommunicator client;
    private Gson gson;

    private String serverHost = "10.0.2.2";
    private String serverPort = "8080";

    public GameLobbyProxy(String host, String port)  {
        gson = new Gson();
    }

    public GameLobbyResult createGame(CreateGameRequest request) {
        System.out.println("This is where we got");
        String requestString = gson.toJson(request);
        System.out.println("GSON did not fail");

        System.out.println(requestString);

        String url = "http://" + serverHost + ":" + serverPort + "/gamelobby/creategame";

        return sendRequest(url, requestString, "POST");
    }

    public GameLobbyResult joinGame(JoinGameRequest request) {
        String requestString = gson.toJson(request);
        String url = "http://" + serverHost + ":" + serverPort + "/gamelobby/joingame";

        return sendRequest(url, requestString, "POST");
    }

    private GameLobbyResult sendRequest(String url, String requestString, String requestType) {
        System.out.println("Got to sendRequest");
        String resultString;
        client = new ClientCommunicator();

        GameLobbyResult results = new GameLobbyResult();

        gson.toJson(results);

        System.out.println("We've got a ClientCommunicator");

        try {
            resultString = client.execute(url, requestType, requestString).get();
        } catch (Exception e) {
            resultString = "Error: Could not connect to the server.";
        }

        if (resultString.equals("Error: Could not connect to the server.")) {
            GameLobbyResult result = new GameLobbyResult();
            result.setMessage(resultString);
            return result;
        }

        System.out.println("We ended up getting this far");
        System.out.println(resultString);

        return gson.fromJson(resultString, GameLobbyResult.class);
    }
}
