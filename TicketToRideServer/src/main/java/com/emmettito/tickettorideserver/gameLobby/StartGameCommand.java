package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.StartGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Results.GameLobbyResult;

import org.omg.CosNaming.NamingContextPackage.NotFound;

public class StartGameCommand implements IGameLobbyCommand {
    @Override
    public Object execute(Object obj, String authToken) throws NotFound {
        StartGameRequest startGameRequest = (StartGameRequest) obj;
        Game currGame = gameLobbyDatabase.getGame(startGameRequest.getGameName());
        if (currGame == null) {
            throw new NotFound();
        }
        gameLobbyDatabase.removeGame(currGame.getGameName());
        gameLobbyDatabase.addActiveGame(currGame);

        GameLobbyResult gameLobbyResult = new GameLobbyResult();
        gameLobbyResult.setSuccess(true);
        return gameLobbyResult;
    }
}
