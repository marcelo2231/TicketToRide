package com.emmettito.tickettorideserver.game;

import com.emmettito.models.Results.Result;

public interface IGameCommand {
    Result execute(Object obj) throws Exception ;
}
