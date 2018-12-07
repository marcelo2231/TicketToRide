package com.emmettito.tickettorideserver.database;

public abstract class AbstractDAOFactory {
    public abstract IUserIMA getUserDAO();
    public abstract IGameIMA getGameDAO();
}
