package com.emmettito.tickettorideserver.database.FlatFile;

import com.emmettito.tickettorideserver.database.AbstractDAOFactory;
import com.emmettito.tickettorideserver.database.IGameIMA;
import com.emmettito.tickettorideserver.database.IUserIMA;

public class FFFactory extends AbstractDAOFactory {

    @Override
    public IUserIMA getUserDAO() {
        return new FFUserDAO();
    }

    @Override
    public IGameIMA getGameDAO() {
        return new FFGameDAO();
    }
}
