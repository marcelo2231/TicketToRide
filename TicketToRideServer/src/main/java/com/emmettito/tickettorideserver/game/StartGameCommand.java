package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.StartGameRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class StartGameCommand implements IGameCommand{
    StartGameRequest commandModel;

    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (StartGameRequest)new Serializer().deserialize((InputStream)obj, StartGameRequest.class);
        }catch(Exception e){
            throw new Exception("StartGameCommand: command was null, please, make sure to set the StartGameCommandModel.");
        }
        if(!userDatabase.authTokenIsValid(authToken)){
            throw new Exception("Invalid authToken. You do not have authorization to execute this command.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
