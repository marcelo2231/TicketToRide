package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.SetGameRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class SetGameCommand implements IGameCommand{
    SetGameRequest commandModel;
    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Validate **/
        try {
            commandModel = (SetGameRequest)new Serializer().deserialize((InputStream)obj, SetGameRequest.class);
        }catch(Exception e){
            throw new Exception("SetGameCommand: command was null, please, make sure to set the SetGameRequest Model.");
        }

        /** Get list of games **/
        gameDao.setGame(commandModel.getGame());

        /** Prepare Result **/
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("Game returned.");
        return result;
    }
}
