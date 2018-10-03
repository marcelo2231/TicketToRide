package com.emmettito.models;

import java.util.Date;
import java.util.UUID;

public class AuthToken {
    /** Constructor **/
    public AuthToken(String username){
        this.username = username;
        authToken = UUID.randomUUID().toString().replace("-", "");
        expirationTime = new Date(new Date().getTime() + (1000 * 60 * 60)); // Time now + 60 minutes
    }

    /** Variables **/
    private String authToken;
    private Date expirationTime;
    private String username;

    /** Getters */
    public String getAuthToken() {
        return authToken;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public String getUsername() {
        return username;
    }

    /** Setters */

    /** Helpers **/
    public boolean isValid(){
        if (expirationTime.compareTo(new Date()) <= 0){
            return false;
        }
        else{
            return true;
        }
    }
}
