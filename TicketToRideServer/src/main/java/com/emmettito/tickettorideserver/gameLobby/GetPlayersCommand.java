package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.models.Results.GetPlayersResult;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;

public class GetPlayersCommand implements IGameLobbyCommand {
    @Override
    public Object execute(Object obj, String authToken) throws Exception {
        GetPlayersRequest getPlayersRequest = (GetPlayersRequest) obj;
        String gameName = getPlayersRequest.getGameName();
        Game targetGame = gameLobbyDatabase.getGame(gameName);

        if (targetGame.equals(null)) {
            throw new Exception();
        }
        else {
            GetPlayersResult getPlayersResult = new GetPlayersResult();
            getPlayersResult.setSuccess(true);
            ArrayList<Player> players = targetGame.getPlayers();
            getPlayersResult.setData(players);
            return getPlayersResult;
        }
    }
}
