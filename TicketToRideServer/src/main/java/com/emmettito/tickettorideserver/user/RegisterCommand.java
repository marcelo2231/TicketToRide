package com.emmettito.tickettorideserver.user;

import com.emmettito.models.CommandModels.UserCommands.RegisterRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class RegisterCommand implements IUserCommand{
    RegisterRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        try{
            commandModel = (RegisterRequest)new Serializer().deserialize((InputStream)obj, RegisterRequest.class);
        }catch (Exception e){
            throw new Exception("RegisterCommand: command was null, please, make sure to set the RegisterCommandModel.");
        }

        // TODO: Store data on Database

        // Result Returns am AuthToken
        return new Result();
    }
}
