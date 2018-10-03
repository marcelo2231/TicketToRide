package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.QuitGameRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class QuitGameCommand implements IGameLobbyCommand{
    QuitGameRequest commandModel;

    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (QuitGameRequest)new Serializer().deserialize((InputStream)obj, QuitGameRequest.class);
        }catch(Exception e){
            throw new Exception("QuitGameCommand: command was null, please, make sure to set the QuitGameCommandModel.");
        }
        if(!gameDatabase.authTokenIsValid(authToken)){
            throw new Exception("Invalid authToken. You do not have authorization to execute this command.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
