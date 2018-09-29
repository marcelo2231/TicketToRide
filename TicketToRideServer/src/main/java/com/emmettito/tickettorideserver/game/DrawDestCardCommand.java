package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommand;
import com.emmettito.models.Result;

public class DrawDestCardCommand implements IGameCommand{
    @Override
    public Result execute(GameCommand obj) throws Exception {
        return new Result();
    }
}
