package com.emmettito.models;

import java.util.Date;
import java.util.UUID;

public class AuthToken {
    /** Constructor **/
    public AuthToken(){
        authToken = UUID.randomUUID().toString().replace("-", "");
        timeCreated = new Date(new Date().getTime() + (1000 * 60 * 60));
    }

    /** Variables **/
    private String authToken;
    private Date timeCreated;

    /** Getters */
    public String getAuthToken() {
        return authToken;
    }

    /** Setters */

    /** Helpers **/
    public boolean isValid(){
        if (timeCreated.compareTo(new Date()) <= 0){
            return false;
        }
        else{
            return true;
        }
    }
}
