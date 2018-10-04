package com.emmettito.tickettorideserver.database;

import com.emmettito.models.AuthToken;
import com.emmettito.models.User;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.omg.PortableInterceptor.ORBInitInfoPackage.InvalidName;

public class UserDao {
    /** Database Instance **/
    private static Database dbInstance = Database.getInstance();

    /** Register **/
    public AuthToken registerUser(User newUser) throws DuplicateName{
        if (userExists(newUser.getUsername())) {
            throw new DuplicateName();
        }
        else {
            dbInstance.users.add(newUser);
        }
        return generateAuthToken(newUser.getUsername());
    }

    /** Login **/

    public AuthToken loginUser(User user) throws InvalidName {
        if (!loginValidation(user)) {
            throw new InvalidName();
        }

        return generateAuthToken(user.getUsername());
    }

    public boolean loginValidation(User user) {
        for (User u : dbInstance.users) {
            if (u.getUsername().equals(user.getUsername())) {
                if (u.getPassword().equals(user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Logout **/

    public void logoutUser(String username) throws InvalidName {
        if (!dbInstance.removeAuthToken(username)) {
            throw new InvalidName();
        }
        return;
    }

    /** AuthToken **/
    public AuthToken generateAuthToken(String username){
        return dbInstance.addAuthToken(username);
    }


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

    public boolean removeUser(String username){
        User toBeRemoved = getUser(username);
        if (toBeRemoved == null){ return false; }
        return dbInstance.users.remove(toBeRemoved);
    }

}
