package com.emmettito.tickettorideserver.database.SQL;

import com.emmettito.tickettorideserver.database.AbstractDAOFactory;
import com.emmettito.tickettorideserver.database.IGameDAO;
import com.emmettito.tickettorideserver.database.IUserDAO;

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
