package com.emmettito.tickettorideserver.database.FlatFile;

import com.emmettito.models.User;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.InternalMemory;
import com.emmettito.tickettorideserver.database.IUserDAO;

import org.apache.commons.io.FileUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FFUserDAO implements IUserDAO {
    Serializer serializer = new Serializer();

    @Override
    public boolean addUser(String username, String password) {
        String fileName = "Users/" + username;
        try {
            String gameJson = serializer.serialize(new User(username, password));
            PrintWriter out = new PrintWriter(fileName);
            out.println(gameJson);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public User getUser(String username) {
        File[] files = new File("Users/").listFiles();
        for (File f : files){
            try{
                User user = (User)serializer.deserialize(new ByteArrayInputStream(f.toString().getBytes()), User.class);
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
        File dir = new File("Users");
        try {
            FileUtils.deleteDirectory(dir);
        }
        catch (Exception e){
        }
    }
}
