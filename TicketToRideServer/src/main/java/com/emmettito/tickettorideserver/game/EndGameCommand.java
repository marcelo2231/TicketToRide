package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.EndGameRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class EndGameCommand implements IGameCommand{
    EndGameRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (EndGameRequest)new Serializer().deserialize((InputStream)obj, EndGameRequest.class);
        }catch (Exception e){
            throw new Exception("EndGameCommand: command was null, please, make sure to set the EndGameCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
