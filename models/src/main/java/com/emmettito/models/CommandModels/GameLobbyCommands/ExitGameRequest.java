package com.emmettito.models.CommandModels.GameLobbyCommands;

public class ExitGameRequest {
    private String userName;
    private String gameName;

    public ExitGameRequest(String userName, String gameName) {
        this.userName = userName;
        this.gameName = gameName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
