package com.emmettito.models.CommandModels.GameLobbyCommands;

public class GetPlayersRequest {
    private String gameName;

    public GetPlayersRequest(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
