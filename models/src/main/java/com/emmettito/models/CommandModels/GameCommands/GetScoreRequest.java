package com.emmettito.models.CommandModels.GameCommands;

public class GetScoreRequest {
    /** Variables **/
    String GameName;

    /** Setters **/
    public void setGameName(String GameName) {
        this.GameName = GameName;
    }

    /** Getters **/
    public String getGameName() {
        return GameName;
    }
}
