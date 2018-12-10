package com.emmettito.tickettorideserver.game;

import com.emmettito.tickettorideserver.database.GameIMA;
import com.emmettito.tickettorideserver.database.UserIMA;

public interface IGameCommand {
    GameIMA gameIMA = new GameIMA();
    UserIMA userIMA = new UserIMA();
    Object execute(Object obj, String authToken) throws Exception ;
}
