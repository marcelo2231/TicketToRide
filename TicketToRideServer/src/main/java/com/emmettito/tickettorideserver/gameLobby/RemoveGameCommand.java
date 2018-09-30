package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommand;
import com.emmettito.models.CommandModels.GameLobbyCommands.RemoveGameCommandModel;
import com.emmettito.models.Result;

public class RemoveGameCommand implements IGameLobbyCommand{
    RemoveGameCommandModel commandModel;

    @Override
    public Result execute(GameLobbyCommand obj) throws Exception {
        if(obj.getRemoveGameCommandModel() != null) {
            commandModel = obj.getRemoveGameCommandModel();
        }else{
            throw new Exception("RemoveGameCommand: command was null, please, make sure to set the RemoveGameCommandModel.");
        }

        return new Result();
    }
}
