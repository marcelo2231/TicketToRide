package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;
import com.emmettito.models.Tuple;

import java.util.ArrayList;

public class ChatDao {
    /** Variables **/
    private static Database dbInstance = Database.getInstance();
    GameLobbyDao gameLobbyDao = new GameLobbyDao();

    /** Chat **/
    public boolean addToChat(String gameName, String playerName, String message){
        Game game = gameLobbyDao.getActiveGame(gameName);
        ArrayList<Tuple> chat = game.getChat();
        Tuple newChatTuple = new Tuple(playerName, message);
        return chat.add(newChatTuple);
    }

    public ArrayList<Tuple> getChat(String gameName){
        Game game = gameLobbyDao.getActiveGame(gameName);
        return game.getChat();
    }
}
