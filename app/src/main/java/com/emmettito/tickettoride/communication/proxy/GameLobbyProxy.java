package com.emmettito.tickettoride.communication.proxy;

import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettoride.communication.ClientCommunicator;
import com.google.gson.Gson;

public class GameLobbyProxy {
    private ClientCommunicator client;

    private String serverHost = "localhost";
    private String serverPort = "8080";

    public GameLobbyProxy(String host, String port)  {
        client = new ClientCommunicator();
    }

    public GameLobbyResult createGame(CreateGameRequest request, String authToken) {
        Gson gson = new Gson();

        String requestString = gson.toJson(request);

        //String url = "http://" + serverHost + ":" + serverPort + "/gamelobby/creategame";

        String url = "http://localhost:8080/gamelobby/creategame";

        String resultString;

        try {
            resultString = client.execute(url, requestString, authToken, "POST").get();
        } catch (Exception e) {
            resultString = "Error: Could not connect to the server.";
        }

        System.out.println(resultString);

        if (resultString.equals("Error: Could not connect to the server.")) {
            GameLobbyResult result = new GameLobbyResult();
            result.setSuccess(false);
            result.setMessage(resultString);
            return result;
        }

        return gson.fromJson(resultString, GameLobbyResult.class);
    }

    //public String login(String userToLogin) {
    //    return client.post("/user/login", userToLogin);
    //}

    //public String person(String authToken) {
    //    return client.get("/person", authToken);
    //}

    //public String event(String authToken) {
    //    return client.get("/event", authToken);
    //}
}
