package com.emmettito.tickettorideserver.database;

import com.emmettito.models.AuthToken;
import com.emmettito.models.User;

public class UserIMA {
    /** InternalMemory Instance **/
    private static InternalMemory dbInstance = InternalMemory.getInstance();


    /** Users **/

    public User getUser(String username) {
        IUserDAO dao = dbInstance.getUserDAO();

        return dao.getUser(username);
    }

    public boolean addUser(User newUser){
        IUserDAO dao = dbInstance.getUserDAO();

        return dao.addUser(newUser.getUsername(), newUser.getPassword());
    }

    public boolean compareUserAndPassword(User user) {
        IUserDAO dao = dbInstance.getUserDAO();

        User user2 = dao.getUser(user.getUsername());

        if (user2.getPassword().equals(user.getPassword())) {
            return true;
        }

        return false;
    }

    /** AuthToken methods**/
    public AuthToken generateAuthToken(String username) throws Exception{
        IUserDAO dao = dbInstance.getUserDAO();

        String authToken = dao.generateAuthToken(username);

        AuthToken token = new AuthToken(username);

        token.setAuthToken(authToken);

        return token;
    }

    public boolean authTokenAndUserAreValid(String authToken, String username){
        IUserDAO dao = dbInstance.getUserDAO();

        return dao.checkAuthToken(username, authToken);
    }

}
