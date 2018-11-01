package com.emmettito.models.CommandModels;

public class Commands {
    private Class commandType;
    private String requestJson;
    private String resultJson;

    public Commands(Class commandType, String requestJson, String resultJson) {
        this.commandType = commandType;
        this.requestJson = requestJson;
        this.resultJson = resultJson;
    }

    public Class getCommandType() {
        return commandType;
    }

    public String getRequestJson() {
        return requestJson;
    }

    public String getResutJson() {
        return resultJson;
    }

    public void setCommandType(Class commandType) {
        this.commandType = commandType;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public void setResutJson(String resultJson) {
        this.resultJson = resultJson;
    }
}
