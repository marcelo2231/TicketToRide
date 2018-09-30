package com.emmettito.models.CommandModels;

public class GameCommandData {
    /** Variables **/
    private GameCommandType type;
    private GameCommand data;

    /** Getters **/
    public GameCommand getData() {
        return data;
    }

    public GameCommandType getType() {

        return type;
    }

    /** Setters **/
    public void setData(GameCommand data) {
        this.data = data;
    }

    public void setType(GameCommandType type) {
        this.type = type;
    }
}
