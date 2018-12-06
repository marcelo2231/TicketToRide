package com.emmettito.tickettorideserver.database;

import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.Game;

import java.util.List;

public class SQLGameDAO implements IGameDAO {

    @Override
    public boolean addGame(Game game) {
        return false;
    }

    @Override
    public boolean addCompletedGame(String gameName) {
        return false;
    }

    @Override
    public Game getGame(String gameName) {
        return null;
    }

    @Override
    public boolean removeGame(String gameName) {
        return false;
    }

    @Override
    public Game updateGame(String gameName, List<Command> commands) {
        return null;
    }

    @Override
    public boolean addCommand(Command command) {
        return false;
    }

    @Override
    public List<Command> getCommands() {
        return null;
    }

    @Override
    public boolean clearCommands() {
        return false;
    }

    @Override
    public boolean clearGames() {
        return false;
    }

    @Override
    public void beginTransation() {

    }

    @Override
    public void endTransaction() {

    }
}
