package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommand;
import com.emmettito.models.CommandModels.GameCommands.GetScoreCommandModel;
import com.emmettito.models.Results.Result;

public class GetScoreCommand implements IGameCommand{
    GetScoreCommandModel commandModel;
    @Override
    public Result execute(GameCommand obj) throws Exception {
        if(obj.getGetScoreCommandModel() != null) {
            commandModel = obj.getGetScoreCommandModel();
        }else{
            throw new Exception("GetScoreCommand: command was null, please, make sure to set the GetScoreCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
