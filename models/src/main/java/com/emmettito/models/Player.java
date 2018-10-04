package com.emmettito.models;

public class Player {
    private String playerName;
    private int points;

    public Player(String playerName){
        this.playerName = playerName;
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
