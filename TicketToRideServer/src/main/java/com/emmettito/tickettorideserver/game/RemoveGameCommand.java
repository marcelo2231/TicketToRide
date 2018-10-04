package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameLobbyCommands.RemoveGameRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class RemoveGameCommand implements IGameCommand {
    RemoveGameRequest commandModel;

    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (RemoveGameRequest)new Serializer().deserialize((InputStream)obj, RemoveGameRequest.class);
        }catch (Exception e){
            throw new Exception("RemoveGameCommand: command was null, please, make sure to set the RemoveGameCommandModel.");
        }
        if(!gameDatabase.authTokenIsValid(authToken)){
            throw new Exception("Invalid authToken. You do not have authorization to execute this command.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
