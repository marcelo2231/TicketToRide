package com.emmettito.tickettorideserver.database;

public abstract class AbstractDAOFactory {
    public abstract IUserDAO getUserDAO();
    public abstract IGameDAO getGameDAO();
}
