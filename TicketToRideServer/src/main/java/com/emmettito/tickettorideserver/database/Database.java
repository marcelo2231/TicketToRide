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
    public ArrayList<Game> activeGame = new ArrayList<>();
    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<AuthToken> tokens = new ArrayList<>();


    /** AuthToken: They are shared with all Dao **/

    public boolean removeAuthToken(String username){
        for (AuthToken a : tokens) {
            if (a.getUsername().equals(username)) {
                tokens.remove(a);
                return true;
            }
        }
        return false;
    }

    public AuthToken addAuthToken(String username){
        AuthToken newAuthToken = new AuthToken(username);
        removeAuthToken(username);
        tokens.add(newAuthToken);
        return newAuthToken;
    }

    public boolean authTokenIsValid(String authToken){
        for (AuthToken a : tokens) {
            if (a.getAuthToken().equals(authToken)) {
                return a.isValid();
            }
        }
        return false;
    }

    public boolean authTokenAndUserAreValid(String authToken, String username){
        for (AuthToken a : tokens) {
            if (a.getAuthToken().equals(authToken)) {
                if(a.getUsername().equals(username)) {
                    return a.isValid();
                }
            }
        }
        return false;
    }

}
