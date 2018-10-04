package com.emmettito.tickettorideserver.game;

import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.database.GameDao;
import com.emmettito.tickettorideserver.database.UserDao;

public interface IGameCommand {
    GameDao gameDatabase = new GameDao();
    UserDao userDatabase = new UserDao();
    Result execute(Object obj, String authToken) throws Exception ;
}
