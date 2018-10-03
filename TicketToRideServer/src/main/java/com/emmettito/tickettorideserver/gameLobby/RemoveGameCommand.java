package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.RemoveGameRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class RemoveGameCommand implements IGameLobbyCommand{
    RemoveGameRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (RemoveGameRequest)new Serializer().deserialize((InputStream)obj, RemoveGameRequest.class);
        }catch (Exception e){
            throw new Exception("RemoveGameCommand: command was null, please, make sure to set the RemoveGameCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
