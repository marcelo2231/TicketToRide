package com.emmettito.tickettorideserver.database;

import com.emmettito.models.User;

public interface IUserIMA {
    boolean addUser(String username, String password);
    User getUser(String username);
    String generateAuthToken(String username);
    boolean checkAuthToken(String authToken);
    void clearDatabase();
    //boolean beginTransaction();
    //boolean endTransaction();
}
