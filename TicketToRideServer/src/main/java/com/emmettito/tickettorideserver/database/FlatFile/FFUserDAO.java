package com.emmettito.tickettorideserver.database.FlatFile;

import com.emmettito.models.User;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.IUserDAO;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FFUserDAO implements IUserDAO {
    Serializer serializer = new Serializer();

    String directory = System.getProperty("user.dir") + "/Users/";

    FFUserDAO() {
        new File(directory).mkdir();
    }

    @Override
    public boolean addUser(String username, String password) {

        String fileName = directory + username + ".txt";
        try {
            String gameJson = serializer.serialize(new User(username, password));
            PrintWriter out = new PrintWriter(fileName);
            System.out.println(gameJson);
            out.println(gameJson);
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User getUser(String username) {
        System.out.println(directory);
        File[] files = new File(directory).listFiles();

        for (File f : files){
            System.out.println(f.getName());
            try{
                String content = new Scanner(f).useDelimiter("\\Z").next();

                User user = (User)serializer.deserialize(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)), User.class);

                if(user.getUsername().equals(username)){
                    return user;
                }
            }catch (Exception e){
            }
        }
        return null;
    }

    @Override
    public String generateAuthToken(String username) {
        return "We know you all hate auth token =D";
    }

    @Override
    public boolean checkAuthToken(String username, String authToken) {
        return true;
    }

    @Override
    public void clearDatabase() {
        File dir = new File(directory);
        try {
            FileUtils.deleteDirectory(dir);
        }
        catch (Exception e){
        }

        dir.mkdir();
    }
}
