package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommand;
import com.emmettito.models.Result;

public interface IGameLobbyCommand {
    Result execute(GameLobbyCommand obj) throws Exception ;
}
