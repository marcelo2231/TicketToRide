package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.util.ArrayList;


public class GameLobbyDao {
    /** Database Instance used to access the required data**/
    private static Database dbInstance = Database.getInstance();

    /** Games */


    /*
     * adds the included game into the database
     * @param newGame: The game that is to be added to the database
     *
     * @pre newGame must have a unique game name
     * @post gameList.size() at beginning must equal gameList.size() + 1 at end
     *
     */
    public void addGame(Game newGame) throws DuplicateName {
        if (getGame(newGame.getGameName()) != null) {
            throw new DuplicateName();
        }
        else {
            dbInstance.gameLobby.add(newGame);
        }
    }

    /*
     * Finds ard returns the game with the selected game name
     *
     * @param gameName The name of the desired game
     *
     *
     */
    public Game getGame(String gameName) {
        for (Game g : dbInstance.gameLobby) {
            if (g.getGameName().equals(gameName)) {
                return g;
            }
        }
        for (Game g : dbInstance.activeGame) {
            if (g.getGameName().equals(gameName)) {
                return g;
            }
        }
        return null;
    }

    public Game getActiveGame(String gameName) {
        for (Game g : dbInstance.activeGame) {
            if (g.getGameName().equals(gameName)) {
                return g;
            }
        }
        return null;
    }

    public Game setGame(Game game) {
        for (Game g : dbInstance.activeGame) {
            if (g.getGameName().equals(game.getGameName())) {
                g = game;
            }
        }
        for (Game g : dbInstance.gameLobby) {
            if (g.getGameName().equals(game.getGameName())) {
                g = game;
            }
        }
        return null;
    }

    public ArrayList<Game> getGames(){
        return dbInstance.gameLobby;
    }

    public ArrayList<Game> getActiveGames(){
        return dbInstance.activeGame;
    }

    public void removeGame(String gameName) throws NotFound {
        Game toBeRemoved = getGame(gameName);
        if (toBeRemoved == null){ throw new NotFound(); }
        if (!dbInstance.gameLobby.remove(toBeRemoved)) { throw new NotFound(); }
    }

    public void addActiveGame(Game newGame) { //two active games can have the same name
        dbInstance.activeGame.add(newGame);
    }


}
