package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettorideserver.database.GameDao;
import com.emmettito.tickettorideserver.database.GameLobbyDao;

public interface IGameLobbyCommand {
    GameLobbyDao gameLobbyDatabase = new GameLobbyDao();
    GameDao gameDatabase = new GameDao();
    Object execute(Object obj, String authToken) throws Exception ;
}
