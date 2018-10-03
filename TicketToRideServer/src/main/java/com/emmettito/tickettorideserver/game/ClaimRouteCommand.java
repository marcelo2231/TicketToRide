package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.ClaimRouteRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class ClaimRouteCommand implements IGameCommand{
    ClaimRouteRequest commandModel;

    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (ClaimRouteRequest)new Serializer().deserialize((InputStream)obj, ClaimRouteRequest.class);
        }catch (Exception e){
            throw new Exception("ClaimRouteCommand: command was null, please, make sure to set the ClaimRouteCommandModel.");
        }
        if(!gameDatabase.authTokenIsValid(authToken)){
            throw new Exception("Invalid authToken. You do not have authorization to execute this command.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
