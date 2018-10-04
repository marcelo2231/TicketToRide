package com.emmettito.tickettorideserver.database;

import com.emmettito.models.AuthToken;
import com.emmettito.models.Game;
import com.emmettito.models.Player;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;

public class GameDao {
    /** Variables **/
    private static Database dbInstance = Database.getInstance();
    private GameLobbyDao gameDao = new GameLobbyDao();

    /** Player **/
    public void addPlayer(String gameName, Player newPlayer) throws NotFound, Exception {
        // gameName Validation
        if (!gameDao.gameExists(gameName)) {
            throw new NotFound();
        }

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
            if(p.getName().equals(newPlayer.getName())){
                throw new Exception("Player already in game.");
            }
        }

        // Add newPlayer
        newList.add(newPlayer);
    }

    public void removePlayer(String gameName, Player targetPlayer) throws NotFound, Exception{
        // gameName Validation
        if (!gameDao.gameExists(gameName)) {
            throw new NotFound();
        }

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

    /** AuthToken methods**/
    public AuthToken generateAuthToken(String username){
        return dbInstance.addAuthToken(username);
    }

    public boolean authTokenIsValid(String authToken){
        return dbInstance.authTokenIsValid(authToken);
    }

    public boolean authTokenAndUserAreValid(String authToken, String username){
        return dbInstance.authTokenAndUserAreValid(authToken, username);
    }
}
