package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;

public interface IGameDAO {
    boolean addGame(Game game);
    boolean removeGame(String gameName);
    Game updateGame(String gameName, String updatedGame);
    boolean addCompletedGame(String gameName);
    Game getGame(String gameName);
    boolean clearDatabase();
}
