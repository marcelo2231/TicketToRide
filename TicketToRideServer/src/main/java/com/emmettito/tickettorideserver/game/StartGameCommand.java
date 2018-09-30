package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommand;
import com.emmettito.models.CommandModels.GameCommands.StartGameCommandModel;
import com.emmettito.models.Result;

public class StartGameCommand implements IGameCommand{
    StartGameCommandModel commandModel;

    @Override
    public Result execute(GameCommand obj) throws Exception {
        if(obj.getStartGameCommandModel() != null) {
            commandModel = obj.getStartGameCommandModel();
        }else{
            throw new Exception("StartGameCommand: command was null, please, make sure to set the StartGameCommandModel.");
        }

        return new Result();
    }
}
