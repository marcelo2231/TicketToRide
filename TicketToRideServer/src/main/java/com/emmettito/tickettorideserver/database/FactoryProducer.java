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

        /*if (!database_type.contains("factory")) {
            database_type.concat("factory");
        }*/

        File file = getPlugin(database_type); // TODO: needs to load plugin

        //System.out.println("Plugin: " + file.getName());

        //String jarString = plugin_directory + "/" + database_type;

        //System.out.println("Here's a thing:");
        //System.out.println(jarString);

        //File file = new File(jarString);

        /***
         *
         * THIS CODE!!!!!!
         *
         */


        System.out.println("Filename: " + file.getName());

        URL url = file.toURI().toURL();
        URL[] urls = {url};
        //System.out.println(urls.length);
        //for (int i = 0; i < urls.length; i++) {
        //    System.out.println("This is the url: " + urls[i].toString());
        //}

        //JSONArray listofClasses = new JSONArray();
        //JSONObject crunchifyObject = new JSONObject();

        String jarName = plugin_directory + "/" + file.getName();

        System.out.println("Jar name: " + jarName);

        Class<?> returnClass = null;

        try {
            JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
            JarEntry jarEntry;

            ClassLoader loader = new URLClassLoader(urls);

            //Class<?> thisClass = new FactoryClassLoader().findClass("This is a name");

            //System.out.println("Is this a class?: " + thisClass.getCanonicalName());

            while (true) {
                jarEntry = jarFile.getNextJarEntry();

                //System.out.println("Jar entry: " + jarEntry.getName());

                if (jarEntry == null) {
                    break;
                }

                if (!jarEntry.getName().endsWith(".class")) {
                    continue;
                }

                if (jarEntry.getName().contains("Factory")) {
                    String className = jarEntry.getName().replaceAll("/", "\\.");
                    className = className.substring(0, className.lastIndexOf('.'));
                    System.out.println("className: " + className);
                    //System.out.println("myClass: " + myClass);
                    //listofClasses.put(myClass);

                    try {
                        returnClass = loader.loadClass(className);
                    } catch (NoClassDefFoundError e) {
                        /*String errorMessage = e.getMessage();
                        System.out.println("Error message: " + errorMessage);
                        errorMessage = errorMessage.substring(errorMessage.lastIndexOf(':') + 2, errorMessage.lastIndexOf(')'));
                        errorMessage = errorMessage.replace('/', '.');
                        System.out.println("New error message: " + errorMessage);

                        InputStream stream = loader.getResourceAsStream("SQLFactory.class");
                        URL stuff = loader.getResource("SQLFactory.class");

                        System.out.println("This is the stream: " + stream);
                        System.out.println("This is the URL: " + stuff);

                        //Class<?> thisClass = loader.loadClass(stuff.getFile());

                        System.out.println(url.getFile());
                        String strings = url.getFile();
                        strings = strings.replace("/", ".");

                        //loader.loadClass(strings);

                        /*ClassLoader loaders = URLClassLoader.newInstance(
                                new URL[] { url },
                                getClass().getClassLoader()
                        );
                        Class<?> clazz = Class.forName("SQLFactory", true, loaders);

                        URL[] thisdfsdf = {stuff};

                        String string = stuff.toString();

                        System.out.println(string);

                        string = string.replace("!/SQLF", "!/SQL/SQLF");
                        string = string.replace('/', '.');
                        string = string.replace(".class", "");
                        string = string.replace("jar:file:.", "");

                        //Class<?> thisClass = loader.loadClass("file:SQL/SQLFactory");

                        System.out.println("sdfsdf");
                        ClassLoader load = new URLClassLoader(thisdfsdf);

                        load.loadClass("SQL/SQLFactory");

                        System.out.println("Things");

                        byte[] buffer;
                        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                        int nextValue = 0;
                        try {
                            while ( (nextValue = stream.read()) != -1 ) {
                                byteStream.write(nextValue);
                            }
                        } catch (IOException s) {
                            s.printStackTrace();
                        }
                        buffer = byteStream.toByteArray();

                        Class<?> classes = new FactoryClassLoader().doThisThing(buffer);
                        //defineClass(name, b, 0, b.length);

                        //returnClass = new FactoryClassLoader().findClass(errorMessage);

                        returnClass = loader.loadClass("com.emmettito.tickettorideserver.database.SQL.SQLFactory");
                        System.out.println("This is the new class: " + returnClass.getCanonicalName());*/
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error: No valid Factory class.");
        }

        Class<?> superclass = returnClass.getSuperclass();

        //System.out.println("This is the superclass: " + superclass.getCanonicalName());

        if (!superclass.equals(AbstractDAOFactory.class)) {
            //System.out.println("It is true!");
            throw new Exception("Error: Factory class does not extend AbstractDAOFactory.");
        }

        Object newFactory = returnClass.newInstance();

        System.out.println("This is the Factory type: " + newFactory.getClass());

        return (AbstractDAOFactory) newFactory;

        /*JarFile jarFile = new JarFile(file);

        Enumeration<? extends JarEntry> enumeration = jarFile.entries();

        while (enumeration.hasMoreElements()) {
            ZipEntry zipEntry = enumeration.nextElement();

            // Is this a class?
            if (zipEntry.getName().endsWith(".class")) {

                // Relative path of file into the jar.
                String className = zipEntry.getName();

                //Class<?> classs = zipEntry.getClass();

                //className = classs.getCanonicalName();

                System.out.println("This is the classname: " + className);

                // Complete class name
                className = className.replace(".class", "").replace("/", ".");
                className = "com.emmettito.tickettorideserver.database.SQL." + className;
                System.out.println("And one... " + className);
                // Load class definition from JVM
                Class<?> clazz = new URLClassLoader(urls).loadClass(className);

                System.out.println("HERE!!!! " + clazz.getCanonicalName());
            }
        }*/

        //ClassLoader loader = new URLClassLoader(urls);
        //Class myClass = loader.loadClass("com.emmettito.tickettorideserver.database.SQL.SQLFactory");
        //Object tester = myClass.newInstance();
        //System.out.println("Did this work?: " + tester.getClass().getCanonicalName());

        /***
         *
         *
         *
         *
         */

        // TODO: actually use plugin here & get rid of this code:

        /*System.out.println(plugin.getName());

        Class thisClass = SQLGameDAO.class;

        System.out.println("This is where I am");
        System.out.println(thisClass.getCanonicalName());

        Class factoryClass = new FactoryClassLoader().findClass(jarString);

        System.out.println(factoryClass.getCanonicalName());

        Constructor constructor = factoryClass.getConstructor();
        Object myClassObject = constructor.newInstance();

        System.out.println(factoryClass.getGenericSuperclass().getClass().getName());
        System.out.println();

        return (AbstractDAOFactory) myClassObject;*/

        /*if (database_type.equalsIgnoreCase("sqlite")) {
            return new SQLFactory();
        }
        else if (database_type.equalsIgnoreCase("flatfile")) {
            return new FFFactory();
        }

        return null;*/

    }
}
