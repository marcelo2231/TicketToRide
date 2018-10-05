package com.emmettito.tickettoride;

import com.emmettito.models.AuthToken;
import com.emmettito.models.User;

public class Client {

    private static Client client = null;

    String token;

    String user;

    String gameName;

    private Client()
    {
        token = "";
        user = "";
        gameName = "";
    }

    public static Client getInstance()
    {
        if (client == null) {
            client = new Client();
        }

        return client;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
