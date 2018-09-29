package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommand;
import com.emmettito.models.Result;

public interface IGameCommand {
    Result execute(GameCommand obj) throws Exception ;
}
