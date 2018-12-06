package com.emmettito.tickettorideserver.database;

public class SQLUserDAO implements IUserDAO {

    @Override
    public boolean addUser(String username, String password) {
        return false;
    }

    @Override
    public String generateAuthToken(String username) {
        return null;
    }

    @Override
    public boolean checkAuthToken(String authToken) {
        return false;
    }

    @Override
    public void clearDatabase() {

    }

    @Override
    public void beginTransaction() {

    }

    @Override
    public void endTransaction() {

    }
}
