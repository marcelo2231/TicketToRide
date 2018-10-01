package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommand;
import com.emmettito.models.CommandModels.GameCommands.EndGameCommandModel;
import com.emmettito.models.Results.Result;

public class EndGameCommand implements IGameCommand{
    EndGameCommandModel commandModel;

    @Override
    public Result execute(GameCommand obj) throws Exception {
        if(obj.getEndGameCommandModel() != null) {
            commandModel = obj.getEndGameCommandModel();
        }else{
            throw new Exception("EndGameCommand: command was null, please, make sure to set the EndGameCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
