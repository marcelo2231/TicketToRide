package com.emmettito.tickettorideserver.user;

import com.emmettito.models.CommandModels.UserCommands.LogoutRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class LogoutCommand implements IUserCommand{
    LogoutRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        /** Cast Object **/
        try{
            commandModel = (LogoutRequest)new Serializer().deserialize((InputStream)obj, LogoutRequest.class);
        }catch(Exception e){
            throw new Exception("LogoutCommand: command was null, please, make sure to set the LogoutCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
