package com.emmettito.tickettorideserver.database;

import com.emmettito.models.AuthToken;
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
    public ArrayList<Game> gameLobby = new ArrayList<>();
    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<AuthToken> tokens = new ArrayList<>();

    /** Shared Methods **/
    public boolean gameExists(String gameName) {
        for (int i = 0; i < gameLobby.size(); i++) {
            if (gameLobby.get(i).getGameName().equals(gameName)) {
                return true;
            }
        }
        return false;
    }

    public boolean userExists(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean loginValidation(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                if (users.get(i).getPassword().equals(user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeAuthToken(String username){
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getUsername().equals(username)) {
                tokens.remove(i);
                return true;
            }
        }
        return false;
    }
}
