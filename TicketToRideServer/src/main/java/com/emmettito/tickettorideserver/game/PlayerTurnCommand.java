package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommand;
import com.emmettito.models.CommandModels.GameCommands.PlayerTurnCommandModel;
import com.emmettito.models.Results.Result;

public class PlayerTurnCommand implements IGameCommand{
    PlayerTurnCommandModel commandModel;

    @Override
    public Result execute(GameCommand obj) throws Exception {
        if(obj.getPlayerTurnCommandModel() != null) {
            commandModel = obj.getPlayerTurnCommandModel();
        }else{
            throw new Exception("PlayerTurnCommand: command was null, please, make sure to set the PlayerTurnCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
