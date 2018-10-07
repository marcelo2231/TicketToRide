package com.emmettito.tickettoride.communication.proxy;

import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.Results.GetPlayersResult;
import com.emmettito.tickettoride.communication.ClientCommunicator;
import com.google.gson.Gson;

public class GameRoomProxy {

    private ClientCommunicator client;
    private Gson gson;

    private String serverHost = "10.0.2.2";
    private String serverPort = "8080";

    public GameRoomProxy()  {
        gson = new Gson();
    }

    public void leaveGame() {

    }

    public void startGame() {

    }

    public GetPlayersResult getPlayers(GetPlayersRequest request) {

        client = new ClientCommunicator();

        String url = "http://" + serverHost + ":" + serverPort + "/gamelobby/creategame"; // TODO: FIX URL

        String resultBody;
        String requestBody = gson.toJson(request);


        try {
            resultBody = client.execute(url, "POST", requestBody).get();
        } catch (Exception e) {
            resultBody = null;
        }

        if (resultBody == null) {
            /*GetPlayersResult getPlayersResult = new GetPlayersResult();
            getPlayersResult.setMessage("Error: Could not connect to the server.");*/
            return null;
        }

        return gson.fromJson(resultBody, GetPlayersResult.class);
    }
}
