package com.emmettito.tickettorideserver.database;

import java.io.File;

public class FactoryProducer {

    String plugin_directory = System.getProperty("user.dir") + "/plugins";

    public boolean isPlugin(String database_type) {
        File[] files = new File(plugin_directory).listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                String filename = files[i].getName();
                filename = filename.substring(0, filename.lastIndexOf('.')); // strips the file extension

                if (filename.equals(database_type)) {
                    return true;
                }
            }
        }

        return false;
    }

    public AbstractDAOFactory getFactory(String database_type) {
        database_type = database_type.toLowerCase();

        if (isPlugin(database_type)) {
            System.out.println(database_type);
            return null;
        }

//        if (database_type.equalsIgnoreCase("sqlite")) {
//            return new SQLFactory();
//        }
//        else if (pluginType.equalsIgnoreCase("flatfile")) {
//            return new FFFactory();
//        }

        return null;    //DAO Type not supported
    }
}
