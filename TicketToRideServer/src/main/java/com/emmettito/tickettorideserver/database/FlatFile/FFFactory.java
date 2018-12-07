package com.emmettito.tickettorideserver.database.FlatFile;

import com.emmettito.tickettorideserver.database.AbstractDAOFactory;
import com.emmettito.tickettorideserver.database.IGameDAO;
import com.emmettito.tickettorideserver.database.IUserDAO;

public class FFFactory extends AbstractDAOFactory {

    @Override
    public IUserDAO getUserDAO() {
        return new FFUserDAO();
    }

    @Override
    public IGameDAO getGameDAO() {
        return new FFGameDAO();
    }
}
