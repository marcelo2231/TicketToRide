package com.emmettito.tickettorideserver.database;

import java.io.File;

public class FactoryProducer {

    String plugin_directory = System.getProperty("user.dir") + "/plugins";

    public File getPlugin(String database_type) throws Exception {
        File folder = new File(plugin_directory);

        if (!folder.exists()) {
            throw new Exception("Error: plugin folder does not exist");
        }

        File[] files = folder.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                String filename = files[i].getName();
                filename = filename.substring(0, filename.lastIndexOf('.')); // strips the file extension

                if (filename.equals(database_type)) {
                    return files[i];
                }
            }
        }

        throw new Exception("Error: \"" + database_type + "\" plugin does not exist");
    }

    public AbstractDAOFactory getFactory(String database_type) throws Exception {
        database_type = database_type.toLowerCase();

        File plugin = getPlugin(database_type); // TODO: needs to load plugin

//        if (pluginType.equalsIgnoreCase("sqlite")) {
//            return new SQLFactory();
//        }
//        else if (pluginType.equalsIgnoreCase("flatfile")) {
//            return new FFFactory();
//        }

        return null;    //DAO Type not supported
    }
}
