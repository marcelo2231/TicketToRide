package com.emmettito.tickettorideserver.database.SQL;

import com.emmettito.tickettorideserver.database.AbstractDAOFactory;
import com.emmettito.tickettorideserver.database.IGameIMA;
import com.emmettito.tickettorideserver.database.IUserIMA;

public class SQLFactory extends AbstractDAOFactory {

    @Override
    public IGameIMA getGameDAO() {
        return new SQLGameIMA();
    }

    @Override
    public IUserIMA getUserDAO() {
        return new SQLUserIMA();
    }
}
