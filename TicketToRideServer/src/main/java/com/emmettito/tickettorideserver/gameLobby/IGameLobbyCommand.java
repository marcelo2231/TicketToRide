package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.database.GameDao;
import com.emmettito.tickettorideserver.database.GameLobbyDao;

public interface IGameLobbyCommand {
    GameLobbyDao gameLobbyDatabase = new GameLobbyDao();
    GameDao gameDatabase = new GameDao();
    Result execute(Object obj, String authToken) throws Exception ;
}
