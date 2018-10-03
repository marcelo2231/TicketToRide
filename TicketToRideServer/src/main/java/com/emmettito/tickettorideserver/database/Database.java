package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.User;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.util.ArrayList;

public class Database {
    /** Constructor and instance **/
    private static Database instance;
    private Database(){
    }
    public static Database getInstance(){
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }

    /** Stored Variables **/
    public ArrayList<Game> gameLobby = new ArrayList<Game>();

    /** Shared Methods **/
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
