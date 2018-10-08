package com.emmettito.tickettoride.communication.proxy;

import android.util.Log;

import com.emmettito.models.CommandModels.UserCommands.LoginRequest;
import com.emmettito.models.CommandModels.UserCommands.LogoutRequest;
import com.emmettito.models.CommandModels.UserCommands.RegisterRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettoride.communication.ClientCommunicator;
import com.google.gson.Gson;

public class LoginProxy {

    private ClientCommunicator client;
    private Gson gson;

    private String host = "http://10.0.2.2:8080";

    public LoginProxy() {
        gson = new Gson();
    }

    public Result login(LoginRequest request) {

        client = new ClientCommunicator();

        String url = host + "/user/login";

        String resultBody;
        String requestBody = gson.toJson(request);

        try {
            resultBody = client.execute(url, "POST", requestBody).get();
        } catch (Exception e) {
            resultBody = null;
        }

        if (resultBody == null) {
            return null;
        }

        return gson.fromJson(resultBody, Result.class);
    }

    public Result register(RegisterRequest request) {

        client = new ClientCommunicator();

        String url = host + "/user/register";

        String resultBody;
        String requestBody = gson.toJson(request);

        try {
            resultBody = client.execute(url, "POST", requestBody).get();
        } catch (Exception e) {
            resultBody = null;
            Log.w("myApp", "failed: " + e.toString());
        }

        if (resultBody == null) {
            return null;
        }

        Log.w("myApp", resultBody);

        return gson.fromJson(resultBody, Result.class);
    }


    public Result logout(LogoutRequest request) {
        String requestBody = gson.toJson(request);
        String url = host + "/user/logout";
        String resultBody;
        client = new ClientCommunicator();

        try {
            resultBody = client.execute(url, "POST", requestBody).get();
        } catch (Exception e) {
            resultBody = null;
            Log.w("myApp", "failed: " + e.toString());
        }
        if (resultBody == null) {
            return null;
        }
        Log.w("myApp", resultBody);
        return gson.fromJson(resultBody, Result.class);
    }
}
