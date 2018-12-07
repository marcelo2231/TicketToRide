package com.emmettito.tickettorideserver.database.SQL;

import com.emmettito.tickettorideserver.database.IUserDAO;

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
