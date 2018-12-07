package com.emmettito.tickettorideserver.database;

import com.emmettito.models.AuthToken;
import com.emmettito.models.Game;
import com.emmettito.models.User;

import java.util.ArrayList;

public class InternalMemory {
    /** Constructor and instance **/
    private static InternalMemory instance;
    private IGameIMA gameDAO = null;
    private IUserIMA userDAO = null;
    private InternalMemory(){
    }
    public static InternalMemory getInstance(){
        if (instance == null){
            instance = new InternalMemory();
        }
        return instance;
    }

    /** Stored Variables **/
    public ArrayList<Game> gameLobby = new ArrayList<>();
    public ArrayList<Game> activeGame = new ArrayList<>();
    public ArrayList<Game> endedGame = new ArrayList<>();
    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<AuthToken> tokens = new ArrayList<>();

    public void setGameDAO(IGameIMA dao) {
        gameDAO = dao;
    }

    public IGameIMA getGameDAO() {
        return gameDAO;
    }

    public void setUserDAO(IUserIMA dao) {
        userDAO = dao;
    }

    public IUserIMA getUserDAO() {
        return userDAO;
    }
}
