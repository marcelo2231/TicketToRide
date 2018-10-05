package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.util.ArrayList;


public class GameLobbyDao {
    /** Database Instance **/
    private static Database dbInstance = Database.getInstance();

    /** Games */

    public void addGame(Game newGame) throws DuplicateName {
        if (getGame(newGame.getGameName()) != null) {
            throw new DuplicateName();
        }
        else {
            dbInstance.gameLobby.add(newGame);
        }
    }

    public Game getGame(String gameName) {
        for (Game g : dbInstance.gameLobby) {
            if (g.getGameName().equals(gameName)) {
                return g;
            }
        }
        for (Game g : dbInstance.activeGame) {
            if (g.equals(gameName)) {
                return g;
            }
        }
        return null;
    }

    public ArrayList<Game> getGames(){
        return dbInstance.gameLobby;
    }

    public void removeGame(String gameName) throws NotFound {
        Game toBeRemoved = getGame(gameName);
        if (toBeRemoved == null){ throw new NotFound(); }
        if (!dbInstance.gameLobby.remove(toBeRemoved)) { throw new NotFound(); }
    }

}
