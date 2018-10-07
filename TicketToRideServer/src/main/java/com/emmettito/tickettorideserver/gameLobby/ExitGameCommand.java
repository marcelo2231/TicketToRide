package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.ExitGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Results.GameLobbyResult;

import org.omg.CosNaming.NamingContextPackage.NotFound;

public class ExitGameCommand implements IGameLobbyCommand {
    @Override
    public Object execute(Object obj, String authToken) throws Exception {
        ExitGameRequest exitGameRequest = (ExitGameRequest) obj;

        Game targetGame = gameLobbyDatabase.getGame(exitGameRequest.getGameName());
        if (targetGame.equals(null)) {
            throw new Exception("Error, game not found");
        }
        if (!targetGame.playerInGame(exitGameRequest.getUserName())) {
            throw new NotFound();
        }
        else {
            targetGame.removePlayer(exitGameRequest.getUserName());
        }
        GameLobbyResult gameLobbyResult = new GameLobbyResult();
        gameLobbyResult.setSuccess(true);
        return gameLobbyResult;
    }
}
