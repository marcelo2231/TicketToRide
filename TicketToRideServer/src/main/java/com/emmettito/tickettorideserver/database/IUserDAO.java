package com.emmettito.tickettorideserver.database;

public interface IUserDAO {
    boolean addUser(String username, String password);
    String generateAuthToken(String username);
    boolean checkAuthToken(String authToken);
    void clearDatabase();
    void beginTransaction();
    void endTransaction();
}
