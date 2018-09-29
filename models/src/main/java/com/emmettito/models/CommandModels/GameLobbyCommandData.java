package com.emmettito.models.CommandModels;

public class GameLobbyCommandData {
    /** Variables **/
    private GameLobbyCommandType type;
    private GameLobbyCommand data;

    /** Getters **/
    public GameLobbyCommand getData() {

        return data;
    }

    public GameLobbyCommandType getType() {

        return type;
    }

    /** Setters **/
    public void setData(GameLobbyCommand data) {
        this.data = data;
    }

    public void setType(GameLobbyCommandType type) {
        this.type = type;
    }
}
