package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommand;
import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameCommandModel;
import com.emmettito.models.Result;

public class CreateGameCommand implements IGameLobbyCommand{
    CreateGameCommandModel commandModel;

    @Override
    public Result execute(GameLobbyCommand obj) throws Exception {
        if(obj.getCreateGameCommandModel() != null) {
            commandModel = obj.getCreateGameCommandModel();
        }else{
            throw new Exception("CreateGameCommand: command was null, please, make sure to set the CreateGameCommandModel.");
        }

        return new Result();
    }
}
