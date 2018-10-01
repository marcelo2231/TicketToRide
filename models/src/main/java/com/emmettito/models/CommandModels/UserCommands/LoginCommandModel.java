package com.emmettito.models.CommandModels.UserCommands;

public class LoginCommandModel {
    /** Variables **/
    String username;
    String password;

    /** Constructors **/
    public LoginCommandModel(){}

    public LoginCommandModel(String username, String password){
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
