package com.emmettito.models.Results;

public class Result {
    /** Variables */
    private Boolean success;
    private String message;
    private Object data;

    /** Constructors */
    public Result() {
        success = false;
        message = "Default Message";
    }

    public Result(Boolean success, Object data) {

        this.success = success;
        this.data = data;
    }

    public Result(Boolean success, String message) {

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
