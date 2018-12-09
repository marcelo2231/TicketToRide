package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;

import java.util.ArrayList;

public class InternalMemory {
    /** Constructor and instance **/
    private static InternalMemory instance;
    private IGameDAO gameDAO = null;
    private IUserDAO userDAO = null;
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
    //public ArrayList<User> users = new ArrayList<>();
    //public ArrayList<AuthToken> tokens = new ArrayList<>();

    public void setGameDAO(IGameDAO dao) {
        gameDAO = dao;
    }

    public IGameDAO getGameDAO() {
        return gameDAO;
    }

    public void setUserDAO(IUserDAO dao) {
        userDAO = dao;
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }
}
