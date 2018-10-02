package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.DrawDestCardRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class DrawDestCardCommand implements IGameCommand{
    DrawDestCardRequest commandModel;
    @Override
    public Result execute(Object obj) throws Exception {
        try {
            commandModel = (DrawDestCardRequest)new Serializer().deserialize((InputStream)obj, DrawDestCardRequest.class);
        }catch (Exception e){
            throw new Exception("DrawDestCardCommand: command was null, please, make sure to set the DrawDestCardCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
