package com.emmettito.models.Results;

import com.emmettito.models.Player;

import java.util.ArrayList;

public class GetPlayersResult {
    /** Variables */
    private Boolean success;
    private String message;
    private ArrayList<Player> data;

    /** Constructors */
    public GetPlayersResult() {
        success = false;
        message = "Default Message";
    }

    public GetPlayersResult(Boolean success, ArrayList<Player> data) {

        this.success = success;
        this.data = data;
    }

    public GetPlayersResult(Boolean success, String message) {

        this.success = success;
        this.message = message;
    }

    /** Getters */
    public Boolean getSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
    public ArrayList<Player> getData() {
        return data;
    }

    /** Setters */
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(ArrayList<Player> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o){
        if(o == null){ return false; }
        if(this == o){ return true; }
        if (o.getClass() != Result.class){ return false;}
        Result p = (Result) o;
        if(!p.getSuccess().equals(this.success)) { return false; }
        if(!p.getMessage().equals(this.message)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return success.hashCode() + message.hashCode();
    }
}