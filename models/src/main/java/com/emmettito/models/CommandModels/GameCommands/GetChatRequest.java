package com.emmettito.models.CommandModels.GameCommands;

public class GetChatRequest {
    private String gameName;

    private int currIndex;

    public GetChatRequest(String gameName, int currIndex) {
        this.gameName = gameName;
        this.currIndex = currIndex;
    }

    public int getCurrIndex() {
        return currIndex;
    }

    public void setCurrIndex(int currIndex) {
        this.currIndex = currIndex;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
