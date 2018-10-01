package com.emmettito.models.CommandModels;

public class UserCommandData {
    /** Variables **/
    private UserCommandType type;
    private UserCommand data;

    /** Getters **/
    public UserCommand getData() {

        return data;
    }

    public UserCommandType getType() {

        return type;
    }

    /** Setters **/
    public void setData(UserCommand data) {
        this.data = data;
    }

    public void setType(UserCommandType type) {
        this.type = type;
    }
}
