package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.Game;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.models.Results.GetGamesResult;

import java.util.ArrayList;

public class GetGamesCommand implements IGameLobbyCommand {
    @Override
    public GetGamesResult execute(Object obj, String authToken) throws Exception {
        ArrayList<Game> games;
        GetGamesResult result = new GetGamesResult();
        games = gameLobbyDatabase.getGames();

        if (games == null){
            games = new ArrayList<>();
        }

        // Result Returns a Player for the user
        result.setSuccess(true);
        result.setData(games);
        result.setMessage("List of games returned.");
        return result;
    }
}
