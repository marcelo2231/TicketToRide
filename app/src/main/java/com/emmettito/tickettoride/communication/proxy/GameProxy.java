package com.emmettito.tickettoride.communication.proxy;

import com.emmettito.models.CommandModels.GameCommands.ChatRequest;
import com.emmettito.models.CommandModels.GameCommands.DiscardCardRequest;
import com.emmettito.models.CommandModels.GameCommands.DrawDestCardRequest;
import com.emmettito.models.CommandModels.GameCommands.DrawTrainRequest;
import com.emmettito.models.CommandModels.GameCommands.GetCommandsRequest;
import com.emmettito.models.CommandModels.GameCommands.GetGameRequest;
import com.emmettito.models.CommandModels.GameCommands.PlayerTurnRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.Results.ChatResult;
import com.emmettito.models.Results.DrawDestCardResult;
import com.emmettito.models.Results.DrawTrainResult;
import com.emmettito.models.Results.GetCommandsResult;
import com.emmettito.models.Results.GetGameResult;
import com.emmettito.models.Results.GetPlayersResult;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.communication.ClientCommunicator;
import com.google.gson.Gson;

public class GameProxy {
    private ClientCommunicator client;
    private Gson gson;

    private String serverHost = "10.0.2.2";
    private String serverPort = "8080";

    public GameProxy(String host, String port) {
        client = new ClientCommunicator();
        gson = new Gson();
    }

    public ChatResult sendChatMessage(ChatRequest request) {
        String requestString = gson.toJson(request);
        String resultString = "";


        Client clientInstance = Client.getInstance();
        serverHost = clientInstance.getIpAddress();
        String url = "http://" + serverHost + ":" + serverPort + "/game/chat";

        try {
            resultString = client.execute(url, "POST", requestString).get();
        } catch (Exception e) {
            e.printStackTrace();
            resultString = "Error: Could not connect to the server.";
        }

        if (resultString.equals("Error: Could not connect to the server.")) {
            ChatResult result = new ChatResult();
            result.setMessage(resultString);
            return result;
        }

        return gson.fromJson(resultString, ChatResult.class);
    }


    public GetPlayersResult getPlayers(GetPlayersRequest request) {
        String requestString = gson.toJson(request);
        String resultString = "";


        Client clientInstance = Client.getInstance();
        serverHost = clientInstance.getIpAddress();
        String url = "http://" + serverHost + ":" + serverPort + "/gamelobby/getplayers";

        try {
            resultString = client.execute(url, "POST", requestString).get();
        } catch (Exception e) {
            e.printStackTrace();
            resultString = "Error: Could not connect to the server.";
        }

        if (resultString.equals("Error: Could not connect to the server.")) {
            GetPlayersResult result = new GetPlayersResult();
            result.setMessage(resultString);
            return result;
        }

        return gson.fromJson(resultString, GetPlayersResult.class);
    }

    public GetGameResult getGame(GetGameRequest request) {
        String requestString = gson.toJson(request);
        String resultString = "";


        Client clientInstance = Client.getInstance();
        serverHost = clientInstance.getIpAddress();
        String url = "http://" + serverHost + ":" + serverPort + "/game/getgame";

        try {
            resultString = client.execute(url, "POST", requestString).get();
        } catch (Exception e) {
            e.printStackTrace();
            resultString = "Error: Could not connect to the server.";
        }

        if (resultString.equals("Error: Could not connect to the server.")) {
            GetGameResult result = new GetGameResult();
            result.setMessage(resultString);
            return result;
        }

        return gson.fromJson(resultString, GetGameResult.class);
    }


    public DrawDestCardResult drawDestCard(DrawDestCardRequest request) {
        String requestString = gson.toJson(request);
        String resultString = "";


        Client clientInstance = Client.getInstance();
        serverHost = clientInstance.getIpAddress();
        String url = "http://" + serverHost + ":" + serverPort + "/game/drawdestcard";

        try {
            resultString = client.execute(url, "POST", requestString).get();
        } catch (Exception e) {
            e.printStackTrace();
            resultString = "Error: Could not connect to the server.";
        }

        if (resultString.equals("Error: Could not connect to the server.")) {
            DrawDestCardResult result = new DrawDestCardResult();
            result.setMessage(resultString);
            return result;
        }

        return gson.fromJson(resultString, DrawDestCardResult.class);
    }

    public Result discardDestCard(DiscardCardRequest request) {
        String requestString = gson.toJson(request);
        String resultString = "";


        Client clientInstance = Client.getInstance();
        serverHost = clientInstance.getIpAddress();
        String url = "http://" + serverHost + ":" + serverPort + "/game/discarddestcard";

        try {
            resultString = client.execute(url, "POST", requestString).get();
        } catch (Exception e) {
            e.printStackTrace();
            resultString = "Error: Could not connect to the server.";
        }

        if (resultString.equals("Error: Could not connect to the server.")) {
            Result result = new Result();
            result.setMessage(resultString);
            return result;
        }

        return gson.fromJson(resultString, Result.class);
    }


    public GetCommandsResult getCommands(GetCommandsRequest request) {
        String requestString = gson.toJson(request);
        String resultString = "";


        Client clientInstance = Client.getInstance();
        serverHost = clientInstance.getIpAddress();
        String url = "http://" + serverHost + ":" + serverPort + "/game/getcommands";

        try {
            resultString = client.execute(url, "POST", requestString).get();
        } catch (Exception e) {
            e.printStackTrace();
            resultString = "Error: Could not connect to the server.";
        }

        if (resultString.equals("Error: Could not connect to the server.")) {
            GetCommandsResult result = new GetCommandsResult();
            result.setMessage(resultString);
            return result;
        }

        return gson.fromJson(resultString, GetCommandsResult.class);
    }

    public Result endTurn(PlayerTurnRequest request){
        String requestString = gson.toJson(request);
        String resultString = "";


        Client clientInstance = Client.getInstance();
        serverHost = clientInstance.getIpAddress();
        String url = "http://" + serverHost + ":" + serverPort + "/game/playerturn";

        try{
            resultString = client.execute(url, "POST", requestString).get();
        } catch (Exception e){
            e.printStackTrace();
            resultString = "Error: could not connect to server";
        }

        if(resultString.equalsIgnoreCase("Error: could not connect to server")){
            Result result = new Result();
            result.setMessage(resultString);
            return result;
        }
        return gson.fromJson(resultString, Result.class);
    }

    public DrawTrainResult drawTrainCard(DrawTrainRequest request){
        String requestString = gson.toJson(request);
        String resultString = "";

        Client clientInstance = Client.getInstance();
        serverHost = clientInstance.getIpAddress();
        String url = "http://" + serverHost + ":" + serverPort + "/game/drawtraincard";

        try{
            resultString = client.execute(url, "POST", requestString).get();
        }catch (Exception e){
            e.printStackTrace();
            resultString = "Error: could not draw Train card";
        }

        if(resultString.equalsIgnoreCase("Error: could not draw Train card")){
            DrawTrainResult result = new DrawTrainResult();
            result.setMessage(resultString);
            return result;
        }
        return gson.fromJson(resultString, DrawTrainResult.class);
    }
}
