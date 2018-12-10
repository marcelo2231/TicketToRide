package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Game;
import com.emmettito.models.Tuple;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.util.ArrayList;

public class ChatIMA {
    /** Variables **/
    GameIMA mGameIMA = new GameIMA();

    /** Chat **/
    public boolean addToChat(String gameName, String playerName, String message) throws Exception{
        IGameDAO dao = InternalMemory.getInstance().getGameDAO();
        Game game = dao.getGame(gameName);
        if (game == null) { throw new Exception("Invalid game name."); }
        ArrayList<Tuple> chat = game.getChat();
        Tuple newChatTuple = new Tuple(playerName, message);

        boolean success = chat.add(newChatTuple);

        game.setChat(chat);

        dao.updateGame(gameName, new Serializer().serialize(game));

        return success;
    }

    public ArrayList<Tuple> getChat(String gameName) throws Exception{
        IGameDAO dao = InternalMemory.getInstance().getGameDAO();
        Game game = dao.getGame(gameName);
        if (game == null) {
            throw new Exception("Invalid game name.");
        }
        return game.getChat();
    }
}
