package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.GetScoreRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class GetScoreCommand implements IGameCommand{
    GetScoreRequest commandModel;
    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (GetScoreRequest)new Serializer().deserialize((InputStream)obj, GetScoreRequest.class);
        }catch (Exception e){
            throw new Exception("GetScoreCommand: command was null, please, make sure to set the GetScoreCommandModel.");
        }
        if(!gameDatabase.authTokenIsValid(authToken)){
            throw new Exception("Invalid authToken. You do not have authorization to execute this command.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
