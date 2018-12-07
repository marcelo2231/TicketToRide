package com.emmettito.tickettorideserver.database.FlatFile;

import com.emmettito.models.User;
import com.emmettito.tickettorideserver.database.Database;
import com.emmettito.tickettorideserver.database.IUserDAO;

import java.util.ArrayList;

public class FFUserDAO implements IUserDAO {
    Database database = Database.getInstance();

    @Override
    public boolean addUser(String username, String password) {
        return database.users.add(new User(username, password));
    }

    @Override
    public String generateAuthToken(String username) {
        return "We know you all hate auth token =D";
    }

    @Override
    public boolean checkAuthToken(String authToken) {
        return false;
    }

    @Override
    public void clearDatabase() {
        database.gameLobby = new ArrayList<>();
        database.activeGame = new ArrayList<>();
        database.endedGame = new ArrayList<>();
        database.users = new ArrayList<>();
        database.tokens = new ArrayList<>();
    }

    @Override
    public void beginTransaction() {

    }

    @Override
    public void endTransaction() {

    }
}
