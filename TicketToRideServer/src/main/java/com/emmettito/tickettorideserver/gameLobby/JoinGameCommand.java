package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.JoinGameRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class JoinGameCommand implements IGameLobbyCommand{
    JoinGameRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (JoinGameRequest)new Serializer().deserialize((InputStream)obj, JoinGameRequest.class);
        }catch (Exception e){
            throw new Exception("JoinGameCommand: command was null, please, make sure to set the JoinGameCommandModel.");
        }

        // TODO: Store data on Database

        // Result Returns a Player for the user
        return new Result();
    }
}
