package com.emmettito.models.Results;

import com.emmettito.models.CommandModels.Command;

import java.util.ArrayList;

public class GetCommandsResult {
    /** Variables */
    private Boolean success;
    private String message;
    private ArrayList<Command> data;

    /** Constructors */
    public GetCommandsResult() {
        success = false;
        message = "Default Message";
    }

    public GetCommandsResult(Boolean success, ArrayList<Command> data) {

        this.success = success;
        this.data = data;
    }

    public GetCommandsResult(Boolean success, String message) {

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
    public ArrayList<Command> getData() {
        return data;
    }

    /** Setters */
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(ArrayList<Command> data) {
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
