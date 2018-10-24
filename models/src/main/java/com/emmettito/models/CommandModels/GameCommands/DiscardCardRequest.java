package com.emmettito.models.CommandModels.GameCommands;

public class DiscardCardRequest {
    /** Variables **/
    String gameName;
    String playerName;
    int cardID;

    /** Setters **/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    /** Getters **/
    public String getGameName() {
        return gameName;
    }
    public String getPlayerName() {
        return playerName;
    }

    public int getCardID() {
        return cardID;
    }
}
