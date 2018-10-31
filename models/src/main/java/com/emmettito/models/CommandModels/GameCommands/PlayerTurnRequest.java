package com.emmettito.models.CommandModels.GameCommands;

public class PlayerTurnRequest {
    /** Variables **/
    String gameName;
    String playerName;
    int gameIndex;

    /** Setters **/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setGameIndex(int index){ this.gameIndex = index; }

    /** Getters **/
    public String getGameName() {
        return gameName;
    }
    public String getPlayerName() {
        return playerName;
    }
    public int getGameIndex(){ return gameIndex; }
}
