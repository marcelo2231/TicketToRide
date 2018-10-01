package com.emmettito.models.CommandModels.UserCommands;

public class RegisterRequest {
    /** Variables **/
    String username;
    String password;

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
