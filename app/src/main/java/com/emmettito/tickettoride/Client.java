package com.emmettito.tickettoride;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.City;
import com.emmettito.models.HardCoded.HardCodedData;
import com.emmettito.models.PlayerColor;
import com.emmettito.models.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Client extends Observable {

    private static Client client = null;

    private String token;
    private String user;
    private int playerID;

    private Game game;
    private String gameName;


    private Client() {
        token = null;
        user = null;
        gameName = null;
        game = null;
    }

    public static Client getInstance() {
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

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void deleteGameName() {
        this.gameName = null;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
