package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.RemoveGameRequest;
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
        if(!userDatabase.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
