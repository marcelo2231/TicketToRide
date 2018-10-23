package com.emmettito.models.CommandModels.GameCommands;

public class GetChatRequest {
    private String gameName;

    public GetChatRequest(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
