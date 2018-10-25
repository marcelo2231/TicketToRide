package com.emmettito.models.CommandModels.GameCommands;

public class ChatRequest {
    /** Variables **/
    String gameName;
    String playerName;
    String message;

    /** Setters **/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    /** Getters **/
    public String getGameName() {
        return gameName;
    }
    public String getPlayerName() {
        return playerName;
    }
    public String getMessage() {
        return message;
    }
}
