package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommand;
import com.emmettito.models.CommandModels.GameLobbyCommands.JoinGameCommandModel;
import com.emmettito.models.Result;

public class JoinGameCommand implements IGameLobbyCommand{
    JoinGameCommandModel commandModel;

    @Override
    public Result execute(GameLobbyCommand obj) throws Exception {
        if(obj.getJoinGameCommandModel() != null) {
            commandModel = obj.getJoinGameCommandModel();
        }else{
            throw new Exception("JoinGameCommand: command was null, please, make sure to set the JoinGameCommandModel.");
        }

        return new Result();
    }
}
