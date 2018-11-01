package com.emmettito.tickettoride;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.City;
import com.emmettito.models.HardCoded.HardCodedData;
import com.emmettito.models.PlayerColor;
import com.emmettito.models.Route;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private static Client client = null;

    String token;
    String user;
    String gameName;

    private List<City> allCities;
    private List<Route> allRoutes;

    private List<Integer> takenRoutes;

    private List<TrainCard> playerTrainCards;

    private Client() {
        token = null;
        user = null;
        gameName = null;

        HardCodedData data = new HardCodedData();
        allCities = data.getCities();
        allRoutes = data.getRoutes();

        takenRoutes = new ArrayList<>();
        playerTrainCards = new ArrayList<>();
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

    public void deleteToken() {
        this.token = null;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void userToken() {
        this.user = null;
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

    /*
    The following could be moved to a different class/activity, but MapView needs to access them somehow
     */

    public List<City> getAllCities() {
        return allCities;
    }

    public List<Route> getAllRoutes() {
        return allRoutes;
    }

    public List<Integer> getTakenRoutes() {
        return takenRoutes;
    }

    public void addToTakenRoutes(int routeID) {
        takenRoutes.add(routeID);
    }

    public void changeRouteColor(int routeID, PlayerColor color) {
        Route route = getAllRoutes().get(routeID);
        route.setPlayerColor(color);
        getAllRoutes().set(routeID, route); //replaces the route with an updated (colored) one
    }

    /*
    Player's Cards
     */

    public List<TrainCard> getPlayerTrainCards() {
        return playerTrainCards;
    }

    public void addTrainCard(TrainCard card) {
        playerTrainCards.add(card);
    }

    public void removeTrainCard(TrainCard card) {
        playerTrainCards.remove(card);
    }
}
