package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;

import java.util.ArrayList;

public interface IGameDAO {
    boolean addGame(Game game, boolean isActive);
    boolean removeGame(String gameName);
    Game updateGame(String gameName, String updatedGame);
    ArrayList<Game> getGames(boolean onlyActiveGames);
    //boolean addCompletedGame(String gameName);
    Game getGame(String gameName);
    boolean clearDatabase();
}
