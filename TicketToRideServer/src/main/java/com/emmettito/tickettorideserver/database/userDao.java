package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;
import com.emmettito.models.User;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.util.ArrayList;


public class userDao {
    //these arrays will store that data until the sql database is implemented
    private ArrayList<User> users = new ArrayList();
    private ArrayList gamesInProgess = new ArrayList();

    void addUser(User newUser) throws DuplicateName {
        if (userExists(newUser.getUsername())) {
            users.add(newUser);
        }
        else {
            throw new DuplicateName();
        }
    }

    void removeUser(String userName) throws NotFound {
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            User currUser = users.get(i);
            if (currUser.getUsername().equals(userName)) {
                users.remove(i);
                found = true;
            }
        }
        if (!found) {
            throw new NotFound();
        }
    }

    public User getUser(String userName) throws NotFound {
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            User currUser = users.get(i);
            if (currUser.getUsername().equals(userName)) {
                found = true;
                return currUser;
            }
        }
        if (!found) {
            throw new NotFound();
        }
        return null;
    }


    boolean userExists(String userName) {
        for (int i = 0; i < users.size(); i++) {
            User currUser = users.get(i);
            if (currUser.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }

}
