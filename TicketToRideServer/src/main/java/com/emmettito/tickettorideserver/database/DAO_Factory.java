package com.emmettito.tickettorideserver.database;

public interface DAO_Factory {
    IUserDAO getUserDAO(String pluginType);
    IGameDAO getGameDAO(String pluginType);
    // void beginTransaction();
    // void endTransaction();
    void clearData();
}
