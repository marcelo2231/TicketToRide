package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.CompleteDestCardRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class CompleteDestCardCommand implements IGameCommand{
    CompleteDestCardRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        try {
            commandModel = (CompleteDestCardRequest)new Serializer().deserialize((InputStream)obj, CompleteDestCardRequest.class);
        }catch(Exception e) {
            throw new Exception("CompleteDestCardCommand: command was null, please, make sure to set the CompleteDestCardCommandModel.");
        }

        // TODO: Store data on Database.

        return new Result();
    }
}
