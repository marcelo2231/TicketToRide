package com.emmettito.models.CommandModels;

public class Command {
    private String playerName;
    private Class commandType;
    private String description;

    public Command(String playerName, Class commandType, String description) {
        this.playerName = playerName;
        this.commandType = commandType;
        this.description = description;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCommandType(Class commandType) {
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

    public Class getCommandType() {
        return commandType;
    }
}
