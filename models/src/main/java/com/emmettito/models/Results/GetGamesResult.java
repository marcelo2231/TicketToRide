package com.emmettito.models.Results;

import com.emmettito.models.Game;

import java.util.ArrayList;

public class GetGamesResult {
    /** Variables */
    private Boolean success;
    private String message;
    private ArrayList<Game> data;

    /** Constructors */
    public GetGamesResult() {
        success = false;
        message = "Default Message";
    }

    public GetGamesResult(Boolean success, ArrayList<Game> data) {

        this.success = success;
        this.data = data;
    }

    public GetGamesResult(Boolean success, String message) {

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
    public ArrayList<Game> getData() {
        return data;
    }

    /** Setters */
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(ArrayList<Game> data) {
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
