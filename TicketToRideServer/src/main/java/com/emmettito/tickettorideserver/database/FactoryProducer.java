package com.emmettito.tickettorideserver.database;

public class FactoryProducer {

    public static AbstractDAOFactory getFactory(String pluginType) {
        if (pluginType.equalsIgnoreCase("sqlite")) {
            return new SQLFactory();
        }
        else if (pluginType.equalsIgnoreCase("flatfile")) {
            return new FFFactory();
        }

        return null;    //DAO Type not supported
    }
}
