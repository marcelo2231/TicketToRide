package com.emmettito.models.CommandModels;

public class Commands {
    private Command command;
    private Object request;
    private Object result;

    public Commands(Command command, Object request, Object result) {
        this.command = command;
        this.request = request;
        this.result = result;
    }

    public Command getCommand() {
        return command;
    }

    public Object getRequest() {
        return request;
    }

    public Object getResult() {
        return result;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
