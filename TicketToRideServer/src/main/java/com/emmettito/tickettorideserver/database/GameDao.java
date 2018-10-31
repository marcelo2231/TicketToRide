package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;
import com.emmettito.models.Player;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;

public class GameDao {
    /** Variables **/
    private static Database dbInstance = Database.getInstance();
    private GameLobbyDao gameDao = new GameLobbyDao();

    /** Player **/
    public void addPlayer(String gameName, Player newPlayer) throws Exception {
        // Get Game
        Game game = gameDao.getGame(gameName);

        // Game Validation
        if (game == null){
            throw new Exception("Game does not exist.");
        }

        // Get Players
        ArrayList<Player> newList = game.getPlayers();

        // Players Validation
        if (newList.size() >= 5) {
            throw new Exception("Error, game has max number of players.");
        }
        for (Player p : newList){
            if(p.getPlayerName().equals(newPlayer.getPlayerName())){
                throw new Exception("Player already in game.");
            }
        }

        // Add newPlayer
        newList.add(newPlayer);
    }

    public void removePlayer(String gameName, Player targetPlayer) throws NotFound, Exception{
        // Get Game
        Game game = gameDao.getGame(gameName);

        // Game Validation
        if (game == null){
            throw new Exception("Game does not exist.");
        }

        // Get Players
        ArrayList<Player> newList = game.getPlayers();

        // Players Validation
        if (newList == null || !newList.remove(targetPlayer)) {
            throw new NotFound();
        }
    }

    public Player getPlayer(String gameName, String playerName) throws NotFound, Exception{
        // Get Game
        Game game = gameDao.getGame(gameName);

        // Game Validation
        if (game == null){
            throw new Exception("Game does not exist.");
        }

        // Find Player
        for(Player p : game.getPlayers()){
            if(p.getPlayerName().equals(playerName)){
                return p;
            }
        }
        return null;
    }

    public ArrayList<Player> getPlayers(String gameName) throws NotFound, Exception{
        // Get Game
        Game game = gameDao.getGame(gameName);

        // Game Validation
        if (game == null){
            throw new Exception("Game does not exist.");
        }

        // Return Player
        return game.getPlayers();
    }

    public int incrementTurn(String gameName) throws Exception{
        // Get Game
        Game game = gameDao.getGame(gameName);

        // Game Validation
        if (game == null){
            throw new Exception("Game does not exist.");
        }

        // Increment turn
        game.incrementTurnIndex();
        return game.getPlayerTurnIndex();
    }

}
