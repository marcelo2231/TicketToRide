package com.emmettito.tickettorideserver.game;

import com.emmettito.tickettorideserver.database.GameIMA;
import com.emmettito.tickettorideserver.database.UserIMA;

public interface IGameCommand {
    GameIMA gameDao = new GameIMA();
    UserIMA userDao = new UserIMA();
    Object execute(Object obj, String authToken) throws Exception ;
}
