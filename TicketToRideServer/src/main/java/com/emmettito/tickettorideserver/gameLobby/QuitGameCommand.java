package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommand;
import com.emmettito.models.CommandModels.GameLobbyCommands.QuitGameCommandModel;
import com.emmettito.models.Results.Result;

public class QuitGameCommand implements IGameLobbyCommand{
    QuitGameCommandModel commandModel;

    @Override
    public Result execute(GameLobbyCommand obj) throws Exception {
        if(obj.getQuitGameCommandModel() != null) {
            commandModel = obj.getQuitGameCommandModel();
        }else{
            throw new Exception("QuitGameCommand: command was null, please, make sure to set the QuitGameCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
