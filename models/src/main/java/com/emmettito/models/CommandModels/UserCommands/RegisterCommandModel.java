package com.emmettito.models.CommandModels.UserCommands;

public class RegisterCommandModel {
    /** Variables **/
    private String username;
    private String password;

    /** Constructors **/
    public RegisterCommandModel(){}

    public RegisterCommandModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    /** Setters **/
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /** Getters **/
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
