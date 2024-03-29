package com.emmettito.models.Results;

import com.emmettito.models.Cards.TrainCard;

public class DrawTrainResult {
    /** Variables */
    private Boolean success;
    private String message;
    private TrainCard data;
    private String renewedAuthToken;

    /** Constructors */
    public DrawTrainResult() {
        success = false;
        message = "Default Message";
    }

    public DrawTrainResult(Boolean success, TrainCard data) {

        this.success = success;
        this.data = data;
    }

    public DrawTrainResult(Boolean success, String message) {

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
    public TrainCard getData() {
        return data;
    }
    public String getRenewedAuthToken() {
        return renewedAuthToken;
    }

    /** Setters */
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(TrainCard data) {
        this.data = data;
    }
    public void setRenewedAuthToken(String renewedAuthToken) {
        this.renewedAuthToken = renewedAuthToken;
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
