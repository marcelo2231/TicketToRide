package com.emmettito.models.CommandModels;

public class Command {
    private String playerName;
    private String commandType;
    private String description;
    private String requestJson;
    private String resultJson;

    public Command(String playerName, String commandType, String description, String requestJson, String resultJson) {
        this.playerName = playerName;
        this.commandType = commandType;
        this.description = description;
        this.requestJson = requestJson;
        this.resultJson = resultJson;
    }

    public String getRequest() {
        return requestJson;
    }

    public String getResult() {
        return resultJson;
    }


    public void setRequest(String requestJson) {
        this.requestJson = requestJson;
    }

    public void setResult(String resultJson) {
        this.resultJson = resultJson;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getCommandType() {
        return commandType;
    }
}
