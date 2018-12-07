package com.emmettito.tickettorideserver.game;

import com.emmettito.models.Game;
import com.emmettito.models.Results.GetGamesResult;

import java.util.ArrayList;

public class GetGamesCommand implements IGameCommand {
    @Override
    public GetGamesResult execute(Object obj, String authToken) throws Exception {
        /** Get list of games **/
        ArrayList<Game> games;
        games = gameDao.getGames();

        if (games == null){
            games = new ArrayList<>();
        }

        /** Prepare Result **/
        GetGamesResult result = new GetGamesResult();
        result.setSuccess(true);
        result.setData(games);
        result.setMessage("List of games returned.");
        return result;
    }
}
