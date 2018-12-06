package com.emmettito.tickettorideserver.database;

public class FFFactory extends AbstractDAOFactory {

    @Override
    public IUserDAO getUserDAO() {
        return null;
    }

    @Override
    public IGameDAO getGameDAO() {
        return null;
    }
}
