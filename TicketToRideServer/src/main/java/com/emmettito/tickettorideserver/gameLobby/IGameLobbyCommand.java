package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.Results.Result;

public interface IGameLobbyCommand {
    Result execute(Object obj) throws Exception ;
}
