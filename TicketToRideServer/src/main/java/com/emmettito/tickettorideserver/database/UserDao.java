package com.emmettito.tickettorideserver.database;

import com.emmettito.models.AuthToken;
import com.emmettito.models.User;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.omg.PortableInterceptor.ORBInitInfoPackage.InvalidName;

public class UserDao {
    /** Database Instance **/
    private static Database dbInstance = Database.getInstance();


    /** Users **/

    public User getUser(String username) {
        for (User u : dbInstance.users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public boolean addUser(User newUser){
        return dbInstance.users.add(newUser);
    }

    public boolean removeUser(String username){
        User toBeRemoved = getUser(username);
        if (toBeRemoved == null){ return false; }
        return dbInstance.users.remove(toBeRemoved);
    }

    public boolean compareUserAndPassword(User user) {
        for (User u : dbInstance.users) {
            if (u.getUsername().equals(user.getUsername())) {
                if (u.getPassword().equals(user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    /** AuthToken methods**/
    public AuthToken generateAuthToken(String username){
        AuthToken newAuthToken = new AuthToken(username);
        removeAuthToken(username);
        dbInstance.tokens.add(newAuthToken);
        return newAuthToken;
    }

    public boolean removeAuthToken(String username){
        for (AuthToken a : dbInstance.tokens) {
            if (a.getUsername().equals(username)) {
                dbInstance.tokens.remove(a);
                return true;
            }
        }
        return false;
    }

    public boolean authTokenAndUserAreValid(String authToken, String username){
        for (AuthToken a : dbInstance.tokens) {
            if (a.getAuthToken().equals(authToken)) {
                if(a.getUsername().equals(username)) {
                    return a.isValid();
                }
            }
        }
        return false;
    }

}
