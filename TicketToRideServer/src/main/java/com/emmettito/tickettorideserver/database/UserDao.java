package com.emmettito.tickettorideserver.database;

import com.emmettito.models.AuthToken;
import com.emmettito.models.User;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.omg.PortableInterceptor.ORBInitInfoPackage.InvalidName;

public class UserDao {
    /** Database Instance **/
    private static Database dbInstance = Database.getInstance();


    /** Users **/

    public boolean userExists(String username){
        if (getUser(username) == null) {
            return false;
        }
        return true;
    }

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
        return dbInstance.addAuthToken(username);
    }

    public boolean removeAuthToken(String username){
        return dbInstance.removeAuthToken(username);
    }

    public boolean authTokenIsValid(String authToken){
        return dbInstance.authTokenIsValid(authToken);
    }

    public boolean authTokenAndUserAreValid(String authToken, String username){
        return dbInstance.authTokenAndUserAreValid(authToken, username);
    }

}
