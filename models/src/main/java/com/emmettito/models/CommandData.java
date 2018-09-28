package com.emmettito.models;

public class CommandData {
    /** Variables **/
    private CommandType type;
    private String data;

    /** Getters **/
    public String getData() {

        return data;
    }

    public CommandType getType() {

        return type;
    }

    /** Setters **/
    public void setData(String data) {
        this.data = data;
    }

    public void setType(CommandType type) {
        this.type = type;
    }
}
