package com.emmettito.tickettorideserver.database;

import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.Game;

import java.util.List;

public interface IGameDAO {
    boolean addGame(Game game);
    Game updateGame(String gameName, List<Command> commands);
    boolean addCompletedGame(String gameName);
    Game getGame(String gameName);
}
