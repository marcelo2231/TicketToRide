package com.emmettito.models.CommandModels.GameCommands;

public class DrawFaceUpTrainRequest {
    /** Variables **/
    String gameName;
    String playerName;
    int cardIndex;

    /** Setters **/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }

    /** Getters **/
    public String getGameName() {
        return gameName;
    }
    public String getPlayerName() {
        return playerName;
    }
    public int getCardIndex() {
        return cardIndex;
    }
}
