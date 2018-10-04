package com.emmettito.models.CommandModels.GameCommands;

public class ClaimRouteRequest {
    /** Variables **/
    String gameName;
    String playerName;

    /** Setters **/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /** Getters **/
    public String getGameName() {
        return gameName;
    }
    public String getPlayerName() {
        return playerName;
    }
}
