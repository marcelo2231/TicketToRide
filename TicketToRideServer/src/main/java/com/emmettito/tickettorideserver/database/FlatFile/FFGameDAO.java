package com.emmettito.tickettorideserver.database.FlatFile;

import com.emmettito.models.Game;
import com.emmettito.tickettorideserver.database.IGameDAO;
import com.emmettito.tickettorideserver.database.InternalMemory;

import java.util.ArrayList;

public class FFGameDAO implements IGameDAO {
    InternalMemory database = InternalMemory.getInstance();

    private Game findActiveGame(String gameName){
        for(Game g : database.activeGame){
            if (g.getGameName().equals(gameName)){
                return g;
            }
        }
        return null;
    }

    private Game findLobbyGame(String gameName){
        for(Game g : database.gameLobby){
            if (g.getGameName().equals(gameName)){
                return g;
            }
        }
        return null;
    }

    @Override
    public boolean addGame(Game game) {
        return database.gameLobby.add(game);
    }

    @Override
    public boolean removeGame(String gameName) {
        Game game = findActiveGame(gameName);
        if (game != null){
            return database.activeGame.remove(game);
        }

        game = findLobbyGame(gameName);
        if (game != null){
            return database.gameLobby.remove(game);
        }

        return false;
    }

    @Override
    public Game updateGame(String gameName, String updatedGame) {
        Game game = findActiveGame(gameName);
        //game.setCommands(new ArrayList<>(commands));
        return game;
    }

    @Override
    public boolean addCompletedGame(String gameName) {
        return false;
    }

    @Override
    public Game getGame(String gameName) {
        Game game = findActiveGame(gameName);
        if (game != null){
            return game;
        }

        game = findLobbyGame(gameName);
        if (game != null){
            return game;
        }

        return null;
    }

    @Override
    public boolean clearDatabase() {
        try {
            database.gameLobby = new ArrayList<>();
            database.activeGame = new ArrayList<>();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
