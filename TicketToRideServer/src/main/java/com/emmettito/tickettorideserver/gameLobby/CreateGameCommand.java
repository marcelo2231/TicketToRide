package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class CreateGameCommand implements IGameLobbyCommand{
    CreateGameRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        try {
            commandModel = (CreateGameRequest)new Serializer().deserialize((InputStream)obj, CreateGameRequest.class);
        }catch (Exception e){
            throw new Exception("CreateGameCommand: command was null, please, make sure to set the CreateGameCommandModel.");
        }

        // TODO: Store data on Database

        // Result Returns a Player for the user
        return new Result();
    }
}
