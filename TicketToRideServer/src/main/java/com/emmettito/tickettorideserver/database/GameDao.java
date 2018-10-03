package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Player;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;

public class GameDao {
    /** Database Instance **/
    private static Database dbInstance = Database.getInstance();

    /** Methods **/
    public void addPlayer(String gameName, Player newPlayer) throws NotFound {
        if (!dbInstance.gameExists(gameName)) {
            throw new NotFound();
        }
        for (int i = 0; i < dbInstance.gameLobby.size(); i++) {
            if (dbInstance.gameLobby.get(i).getGameName().equals(gameName)) {
                ArrayList<Player> newList = dbInstance.gameLobby.get(i).getPlayers();
                newList.add(newPlayer);
                dbInstance.gameLobby.get(i).setPlayers(newList);
            }
        }
    }

    public void removePlayer(String gameName, Player targetPlayer) throws NotFound {
        if (!dbInstance.gameExists(gameName)) {
            throw new NotFound();
        }
        for (int i = 0; i < dbInstance.gameLobby.size(); i++) {
            if (dbInstance.gameLobby.get(i).getGameName().equals(gameName)) {
                ArrayList<Player> newList = dbInstance.gameLobby.get(i).getPlayers();
                if (!newList.remove(targetPlayer)) {
                    throw new NotFound();
                }
                dbInstance.gameLobby.get(i).setPlayers(newList);
            }
        }
    }

    public boolean authTokenIsValid(String authToken){
        return dbInstance.authTokenIsValid(authToken);
    }

    public boolean authTokenAndUserAreValid(String authToken, String username){
        return dbInstance.authTokenAndUserAreValid(authToken, username);
    }
}
