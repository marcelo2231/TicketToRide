package com.emmettito.models;

import java.util.ArrayList;

public class Game {
    /** Variables **/
    String gameName;
    ArrayList<Player> players;

    /** Setters **/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /** Getters **/
    public String getGameName() {
        return gameName;
    }

}
