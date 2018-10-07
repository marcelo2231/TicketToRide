package com.emmettito.models.CommandModels.GameLobbyCommands;

public class StartGameRequest {
    private String gameName;

    public StartGameRequest(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
