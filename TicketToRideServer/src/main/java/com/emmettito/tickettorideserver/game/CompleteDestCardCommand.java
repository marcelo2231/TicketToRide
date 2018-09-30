package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommand;
import com.emmettito.models.CommandModels.GameCommands.CompleteDestCardCommandModel;
import com.emmettito.models.Results.Result;

public class CompleteDestCardCommand implements IGameCommand{
    CompleteDestCardCommandModel commandModel;

    @Override
    public Result execute(GameCommand obj) throws Exception {
        if(obj.getCompleteDestCardCommandModel() != null) {
            commandModel = obj.getCompleteDestCardCommandModel();
        }else{
            throw new Exception("CompleteDestCardCommand: command was null, please, make sure to set the CompleteDestCardCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
