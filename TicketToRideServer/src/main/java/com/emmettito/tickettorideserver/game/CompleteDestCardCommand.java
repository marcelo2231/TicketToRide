package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.CompleteDestCardRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class CompleteDestCardCommand implements IGameCommand{
    CompleteDestCardRequest commandModel;

    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (CompleteDestCardRequest)new Serializer().deserialize((InputStream)obj, CompleteDestCardRequest.class);
        }catch(Exception e) {
            throw new Exception("CompleteDestCardCommand: command was null, please, make sure to set the CompleteDestCardCommandModel.");
        }
        if(!userIMA.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        // TODO: Store data on InternalMemory.

        return new Result();
    }
}
