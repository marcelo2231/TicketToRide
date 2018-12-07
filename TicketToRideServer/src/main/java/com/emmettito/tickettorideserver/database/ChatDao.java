package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;
import com.emmettito.models.Tuple;

import java.util.ArrayList;

public class ChatDao {
    /** Variables **/
    GameDao gameDao = new GameDao();

    /** Chat **/
    public boolean addToChat(String gameName, String playerName, String message) throws Exception{
        Game game = gameDao.getActiveGame(gameName);
        if (game == null) { throw new Exception("Invalid game name."); }
        ArrayList<Tuple> chat = game.getChat();
        Tuple newChatTuple = new Tuple(playerName, message);
        return chat.add(newChatTuple);
    }

    public ArrayList<Tuple> getChat(String gameName) throws Exception{
        Game game = gameDao.getActiveGame(gameName);
        if (game == null) { throw new Exception("Invalid game name."); }
        return game.getChat();
    }
}
