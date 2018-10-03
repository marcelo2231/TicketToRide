package com.emmettito.tickettorideserver.game;

import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.database.GameDao;

public interface IGameCommand {
    GameDao gameDatabase = new GameDao();
    Result execute(Object obj, String authToken) throws Exception ;
}
