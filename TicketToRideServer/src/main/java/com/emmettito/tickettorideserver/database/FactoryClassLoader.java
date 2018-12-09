package com.emmettito.tickettorideserver.database;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FactoryClassLoader extends ClassLoader {
    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }

    public Class doThisThing(byte[] buffer) {
        System.out.println("This is a thing");

        for (int i = 0; i < buffer.length; i++) {
            //System.out.printf(buffer[i]);
        }
        return defineClass("SQL.SQLFactory", buffer, 0, buffer.length);
    }

    private byte[] loadClassFromFile(String fileName)  {
        //fileName = fileName.replace(".jar", ".class");
        fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        //fileName = "com.emettito.tickettorideserver.database.SQL." + fileName;
        //fileName = fileName + ".SQLFactory";
        //fileName = fileName.replace('.', File.separatorChar) + ".class";
        fileName = fileName.concat(".class");
        System.out.println(fileName);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                "SQLFactory.class");

        System.out.println(fileName);
        System.out.println(inputStream);
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}
