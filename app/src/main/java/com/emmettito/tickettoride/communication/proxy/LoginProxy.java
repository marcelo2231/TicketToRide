package com.emmettito.tickettoride.communication.proxy;

import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.CommandModels.UserCommands.LoginRequest;
import com.emmettito.models.CommandModels.UserCommands.RegisterRequest;
import com.emmettito.models.Results.GetPlayersResult;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettoride.communication.ClientCommunicator;
import com.google.gson.Gson;

public class LoginProxy {

    private ClientCommunicator client;
    private Gson gson;

    private String serverHost = "10.0.2.2";
    private String serverPort = "8080";

    public LoginProxy() {
        client = ClientCommunicator.getInstance();
        gson = new Gson();
    }

    public Result login(LoginRequest request, String authToken) {

        String url = "http://" + serverHost + ":" + serverPort + "/user/login";

        String resultBody;
        String requestBody = gson.toJson(request);

        try {
            resultBody = client.execute(url, authToken, "POST", requestBody).get();
        } catch (Exception e) {
            resultBody = null;
        }

        if (resultBody == null) {
            return null;
        }

        return gson.fromJson(resultBody, Result.class);
    }

    public Result register(RegisterRequest request, String authToken) {

        String url = "http://" + serverHost + ":" + serverPort + "/user/register";

        String resultBody;
        String requestBody = gson.toJson(request);

        try {
            resultBody = client.execute(url, authToken, "POST", requestBody).get();
        } catch (Exception e) {
            resultBody = null;
        }

        if (resultBody == null) {
            return null;
        }

        return gson.fromJson(resultBody, Result.class);
    }
}
