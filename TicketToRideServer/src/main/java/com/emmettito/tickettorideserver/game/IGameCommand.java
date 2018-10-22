package com.emmettito.tickettorideserver.game;

import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.database.GameDao;
import com.emmettito.tickettorideserver.database.GameLobbyDao;
import com.emmettito.tickettorideserver.database.UserDao;

public interface IGameCommand {
    GameDao gameDatabase = new GameDao();
    GameLobbyDao gameLobbyDatabase = new GameLobbyDao();
    UserDao userDatabase = new UserDao();
    Object execute(Object obj, String authToken) throws Exception ;
}
