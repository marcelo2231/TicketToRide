package com.emmettito.tickettorideserver.database;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

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

                if (filename.equalsIgnoreCase(database_type)) {
                    System.out.println(filename);
                    return files[i];
                }
            }
        }

        throw new Exception("Error: \"" + database_type + "\" plugin does not exist");
    }

    public AbstractDAOFactory getFactory(String database_type) throws Exception {
        database_type = database_type.toLowerCase();

        File file = getPlugin(database_type); // TODO: needs to load plugin

        URL url = file.toURI().toURL();
        URL[] urls = {url};

        String jarName = plugin_directory + "/" + file.getName();

        Class<?> returnClass = null;

        try {
            JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
            JarEntry jarEntry;

            ClassLoader loader = new URLClassLoader(urls);

            while (true) {
                jarEntry = jarFile.getNextJarEntry();

                if (jarEntry == null) {
                    break;
                }

                if (!jarEntry.getName().endsWith(".class")) {
                    continue;
                }

                if (jarEntry.getName().contains("Factory")) {
                    String className = jarEntry.getName().replaceAll("/", "\\.");
                    className = className.substring(0, className.lastIndexOf('.'));

                    returnClass = loader.loadClass(className);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error: No valid Factory class.");
        }

        Class<?> superclass = returnClass.getSuperclass();

        if (!superclass.equals(AbstractDAOFactory.class)) {
            throw new Exception("Error: Factory class does not extend AbstractDAOFactory.");
        }

        Object newFactory = returnClass.newInstance();

        return (AbstractDAOFactory) newFactory;
    }
}
