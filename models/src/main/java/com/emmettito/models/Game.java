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

    public void addPlayer(Player newPlayer) throws DuplicateName {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(newPlayer.getName())) {
                throw new DuplicateName();
            }
        }
        players.add(newPlayer);
    }
}
