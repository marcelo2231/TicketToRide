package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GetPlayersResult;

import java.util.ArrayList;

public class GetPlayersCommand implements IGameLobbyCommand {
    @Override
    public Object execute(Object obj, String authToken) throws Exception {
        /** Validate **/
        GetPlayersRequest getPlayersRequest = (GetPlayersRequest) obj;

        /** Get game and validate **/
        String gameName = getPlayersRequest.getGameName();
        Game targetGame = gameLobbyDatabase.getGame(gameName);

        if (targetGame.equals(null)) {
            throw new Exception("Game does not exist on database.");
        }

        /** Get players **/
        ArrayList<Player> players = targetGame.getPlayers();
        if(players == null){
            players = new ArrayList<Player>();
        }

        /** Prepare Result **/
        GetPlayersResult getPlayersResult = new GetPlayersResult();
        getPlayersResult.setSuccess(true);
        getPlayersResult.setData(players);
        return getPlayersResult;

    }
}
