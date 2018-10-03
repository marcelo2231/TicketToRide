package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;
import com.emmettito.models.Player;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.util.ArrayList;

public class gameLobbyDao {
    private ArrayList<Game> gameLobby = new ArrayList();

    public void addGame(Game newGame) throws DuplicateName {
        if (gameExists(newGame.getGameName())) {
            throw new DuplicateName();
        }
        else {
            gameLobby.add(newGame);
        }
    }

    public void addPlayer(String gameName, Player newPlayer) throws NotFound {
        if (!gameExists(gameName)) {
            throw new NotFound();
        }
        for (int i = 0; i < gameLobby.size(); i++) {
            if (gameLobby.get(i).getGameName().equals(gameName)) {
                //gameLobby.get(i).addPlayer(newPlayer);
            }
        }
    }

    public void removePlayer(Player targetPlayer) {
        
    }

    public void removeGame(String gameName) throws NotFound {
        boolean found = false;
        for (int i = 0; i < gameLobby.size(); i++) {
            if (gameLobby.get(i).getGameName().equals(gameName)) {
                found = true;
                gameLobby.remove(i);
            }
        }
        if (!found) {
            throw new NotFound();
        }
    }

    public boolean gameExists(String gameName) {
        for (int i = 0; i < gameLobby.size(); i++) {
            Game currGame = gameLobby.get(i);
            if (gameLobby.get(i).getGameName().equals(gameName)) {
                return true;
            }
        }
        return false;
    }
}
