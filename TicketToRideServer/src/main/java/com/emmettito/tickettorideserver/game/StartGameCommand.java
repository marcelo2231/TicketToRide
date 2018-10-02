package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.StartGameRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class StartGameCommand implements IGameCommand{
    StartGameRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        try {
            commandModel = (StartGameRequest)new Serializer().deserialize((InputStream)obj, StartGameRequest.class);
        }catch(Exception e){
            throw new Exception("StartGameCommand: command was null, please, make sure to set the StartGameCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
