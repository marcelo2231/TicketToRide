package com.emmettito.tickettorideserver.game;

import com.emmettito.tickettorideserver.database.GameDao;
import com.emmettito.tickettorideserver.database.UserDao;

public interface IGameCommand {
    GameDao gameDao = new GameDao();
    UserDao userDao = new UserDao();
    Object execute(Object obj, String authToken) throws Exception ;
}
