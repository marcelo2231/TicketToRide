package com.emmettito.models.CommandModels.GameLobbyCommands;

public class CreateGameRequest {
    /** Variables **/
    String gameName;
    String username;

    /** Setters **/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /** Getters **/
    public String getGameName() {
        return gameName;
    }

    public String getUsername() {
        return username;
    }
}
