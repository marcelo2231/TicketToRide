package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Tuple;

import java.util.ArrayList;

public class ChatDao {
    /** Variables **/
    private static Database dbInstance = Database.getInstance();

    /** Chat **/
    public boolean addToChat(String gameName, String playerName, String message){

        return false;
    }
    public ArrayList<Tuple> getChat(String gameName){

        return new ArrayList<Tuple>();
    }

    public ArrayList<Tuple> removeChat(String gameName){

        return new ArrayList<Tuple>();
    }
}
