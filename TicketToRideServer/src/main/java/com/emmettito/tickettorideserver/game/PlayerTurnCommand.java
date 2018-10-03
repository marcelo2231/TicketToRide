package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.PlayerTurnRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class PlayerTurnCommand implements IGameCommand{
    PlayerTurnRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (PlayerTurnRequest)new Serializer().deserialize((InputStream)obj, PlayerTurnRequest.class);
        }catch (Exception e){
            throw new Exception("PlayerTurnCommand: command was null, please, make sure to set the PlayerTurnCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
