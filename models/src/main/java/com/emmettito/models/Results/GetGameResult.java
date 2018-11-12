package com.emmettito.models.Results;

import com.emmettito.models.Game;

public class GetGameResult {
    /** Variables */
    private Boolean success;
    private String message;
    private Game data;

    /** Constructors */
    public GetGameResult() {
        success = false;
        message = "Default Message";
    }

    public GetGameResult(Boolean success, Game data) {

        this.success = success;
        this.data = data;
    }

    public GetGameResult(Boolean success, String message) {

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
    public Game getData() {
        return data;
    }

    /** Setters */
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(Game data) {
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
