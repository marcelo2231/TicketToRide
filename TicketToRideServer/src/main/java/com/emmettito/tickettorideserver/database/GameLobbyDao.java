package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;


public class GameLobbyDao {
    /** Database Instance **/
    private static Database dbInstance = Database.getInstance();

    /** Methods **/
    public void addGame(Game newGame) throws DuplicateName {
        if (dbInstance.gameExists(newGame.getGameName())) {
            throw new DuplicateName();
        }
        else {
            dbInstance.gameLobby.add(newGame);
        }
    }

    public void removeGame(String gameName) throws NotFound {
        boolean found = false;
        for (int i = 0; i < dbInstance.gameLobby.size(); i++) {
            if (dbInstance.gameLobby.get(i).getGameName().equals(gameName)) {
                found = true;
                dbInstance.gameLobby.remove(i);
            }
        }
        if (!found) {
            throw new NotFound();
        }
    }

}
