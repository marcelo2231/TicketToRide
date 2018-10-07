package com.emmettito.models;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.util.ArrayList;

public class Game {
    /** Variables **/
    String gameName;
    ArrayList<Player> players;

    public Game(){
        players = new ArrayList<>();
    }

    /** Setters **/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /** Getters **/
    public String getGameName() {
        return gameName;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getOnePlayer(String userName) {
        boolean found = false;
        int index = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerName().equals(userName));
            index = i;
            found = true;
        }
        if (found) {
            return players.get(index);
        }
        else {
            return null;
        }
    }

    public boolean playerInGame(String userName) {
        boolean found = false;
        int index = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerName().equals(userName));
            index = i;
            found = true;
        }
        return found;
    }

    public void removePlayer(String userName) {
        boolean found = false;
        int index = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerName().equals(userName));
            index = i;
            found = true;
        }
        if (found) {
            players.remove(index);
        }
    }


}
