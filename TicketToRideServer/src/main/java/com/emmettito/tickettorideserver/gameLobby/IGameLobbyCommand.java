package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.tickettorideserver.database.GameDao;
import com.emmettito.tickettorideserver.database.GameLobbyDao;
import com.emmettito.tickettorideserver.database.UserDao;

public interface IGameLobbyCommand {
    GameLobbyDao gameLobbyDatabase = new GameLobbyDao();
    GameDao gameDatabase = new GameDao();
    UserDao userDatabase = new UserDao();
    Object execute(Object obj, String authToken) throws Exception ;
}
