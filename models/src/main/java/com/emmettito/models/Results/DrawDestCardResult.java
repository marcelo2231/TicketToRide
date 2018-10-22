package com.emmettito.models.Results;

public class DrawDestCardResult {
    /** Variables */
    private Boolean success;
    private String message;
    private Object data;
    private String renewedAuthToken;

    /** Constructors */
    public DrawDestCardResult() {
        success = false;
        message = "Default Message";
    }

    public DrawDestCardResult(Boolean success, Object data) {

        this.success = success;
        this.data = data;
    }

    public DrawDestCardResult(Boolean success, String message) {

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
    public Object getData() {
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
    public void setData(Object data) {
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
