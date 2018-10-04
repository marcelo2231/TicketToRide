package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.DrawTrainRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class DrawTrainCommand implements IGameCommand{
    DrawTrainRequest commandModel;

    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (DrawTrainRequest)new Serializer().deserialize((InputStream)obj, DrawTrainRequest.class);
        }catch(Exception e){
            throw new Exception("DrawTrainCommand: command was null, please, make sure to set the DrawTrainCommandModel.");
        }
        if(!userDatabase.authTokenIsValid(authToken)){
            throw new Exception("Invalid authToken. You do not have authorization to execute this command.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
