package com.emmettito.tickettorideserver.database;

import com.emmettito.models.AuthToken;
import com.emmettito.models.User;

public class UserIMA {
    /** InternalMemory Instance **/
    private static InternalMemory dbInstance = InternalMemory.getInstance();


    /** Users **/

    public User getUser(String username) {
        /*for (User u : dbInstance.users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;*/

        IUserDAO dao = dbInstance.getUserDAO();

        return dao.getUser(username);
    }

    public boolean addUser(User newUser){
        //return dbInstance.users.add(newUser);
        IUserDAO dao = dbInstance.getUserDAO();

        return dao.addUser(newUser.getUsername(), newUser.getPassword());
    }

    /*public boolean removeUser(String username){
        User toBeRemoved = getUser(username);
        if (toBeRemoved == null){ return false; }
        return dbInstance.users.remove(toBeRemoved);
    }*/

    public boolean compareUserAndPassword(User user) {
        /*for (User u : dbInstance.users) {
            if (u.getUsername().equals(user.getUsername())) {
                if (u.getPassword().equals(user.getPassword())) {
                    return true;
                }
            }
        }
        return false;*/
        IUserDAO dao = dbInstance.getUserDAO();

        User user2 = dao.getUser(user.getUsername());

        if (user2.getPassword().equals(user.getPassword())) {
            return true;
        }

        return false;
    }

    /** AuthToken methods**/
    public AuthToken generateAuthToken(String username) throws Exception{
        /*try {
            AuthToken newAuthToken = new AuthToken(username);
            removeAuthToken(username);
            dbInstance.tokens.add(newAuthToken);
            return newAuthToken;
        }catch(Exception e){
            throw new Exception("Unable to remove authToken from database and add a new one");
        }*/

        IUserDAO dao = dbInstance.getUserDAO();

        String authToken = dao.generateAuthToken(username);

        AuthToken token = new AuthToken(username);

        token.setAuthToken(authToken);

        return token;
    }

    /*public boolean removeAuthToken(String username){
        for (AuthToken a : dbInstance.tokens) {
            if (a.getUsername().equals(username)) {
                dbInstance.tokens.remove(a);
                return true;
            }
        }
        return false;
    }*/

    public boolean authTokenAndUserAreValid(String authToken, String username){
        /*for (AuthToken a : dbInstance.tokens) {
            if (a.getAuthToken().equals(authToken)) {
                if(a.getUsername().equals(username)) {
                    return a.isValid();
                }
            }
        }
        return false;*/

        IUserDAO dao = dbInstance.getUserDAO();

        return dao.checkAuthToken(username, authToken);
    }

}
