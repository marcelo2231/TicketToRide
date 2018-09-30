package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommand;
import com.emmettito.models.CommandModels.GameCommands.DrawTrainCommandModel;
import com.emmettito.models.Result;

public class DrawTrainCommand implements IGameCommand{
    DrawTrainCommandModel commandModel;

    @Override
    public Result execute(GameCommand obj) throws Exception {
        if(obj.getDrawTrainCommandModel() != null) {
            commandModel = obj.getDrawTrainCommandModel();
        }else{
            throw new Exception("DrawTrainCommand: command was null, please, make sure to set the DrawTrainCommandModel.");
        }

        return new Result();
    }
}
