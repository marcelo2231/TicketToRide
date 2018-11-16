package com.emmettito.tickettoride;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.models.City;
import com.emmettito.models.Game;
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
    private String ipAddress;

    private List<City> allCities;
    private List<Route> allRoutes;

    private TrainColor tempColorChoice;

    private Client() {
        token = null;
        user = null;
        gameName = null;
        game = null;
        ipAddress = "10.0.2.2";

        HardCodedData data = new HardCodedData();
        allCities = data.getCities();
        allRoutes = data.getRoutes();
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

    public List<City> getAllCities() {
        return allCities;
    }

    public void setAllCities(List<City> allCities) {
        this.allCities = allCities;
    }

    public List<Route> getAllRoutes() {
        return allRoutes;
    }

    public void setAllRoutes(List<Route> allRoutes) {
        this.allRoutes = allRoutes;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public TrainColor getTempColorChoice() {
        return tempColorChoice;
    }

    public void setTempColorChoice(TrainColor color) {
        this.tempColorChoice = color;
    }

    public void resetTempColorChoice() {
        this.tempColorChoice = null;
    }
}
