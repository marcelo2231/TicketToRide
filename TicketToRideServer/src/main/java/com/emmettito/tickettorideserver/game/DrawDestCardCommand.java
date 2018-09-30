package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommand;
import com.emmettito.models.CommandModels.GameCommands.DrawDestCardCommandModel;
import com.emmettito.models.Result;

public class DrawDestCardCommand implements IGameCommand{
    DrawDestCardCommandModel commandModel;
    @Override
    public Result execute(GameCommand obj) throws Exception {
        if(obj.getDrawDestCardCommandModel() != null) {
            commandModel = obj.getDrawDestCardCommandModel();
        }else{
            throw new Exception("DrawDestCardCommand: command was null, please, make sure to set the DrawDestCardCommandModel.");
        }

        return new Result();
    }
}
