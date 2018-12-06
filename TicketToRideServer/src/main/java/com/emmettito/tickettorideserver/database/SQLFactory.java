package com.emmettito.tickettorideserver.database;

public class SQLFactory extends AbstractDAOFactory {

    @Override
    public IGameDAO getGameDAO() {
        return new SQLGameDAO();
    }

    @Override
    public IUserDAO getUserDAO() {
        return new SQLUserDAO();
    }
}
