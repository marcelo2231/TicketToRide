package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommand;
import com.emmettito.models.CommandModels.GameCommands.GetScoreCommandModel;
import com.emmettito.models.Result;

public class GetScoreCommand implements IGameCommand{
    GetScoreCommandModel commandModel;
    @Override
    public Result execute(GameCommand obj) throws Exception {
        if(obj.getGetScoreCommandModel() != null) {
            commandModel = obj.getGetScoreCommandModel();
        }else{
            throw new Exception("GetScoreCommand: command was null, please, make sure to set the GetScoreCommandModel.");
        }

        return new Result();
    }
}
