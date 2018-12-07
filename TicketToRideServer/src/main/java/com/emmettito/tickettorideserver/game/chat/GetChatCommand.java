package com.emmettito.tickettorideserver.game.chat;

import com.emmettito.models.CommandModels.GameCommands.GetChatRequest;
import com.emmettito.models.Results.ChatResult;
import com.emmettito.models.Tuple;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.ChatDao;
import com.emmettito.tickettorideserver.game.IGameCommand;

import java.io.InputStream;
import java.util.ArrayList;

public class GetChatCommand  implements IGameCommand {
    GetChatRequest commandModel;
    ChatDao chatDatabase = new ChatDao();

    @Override
    public ChatResult execute(Object obj, String authToken) throws Exception {
        /** Validate **/
        try{
            commandModel = (GetChatRequest)new Serializer().deserialize((InputStream)obj, GetChatRequest.class);
        }catch(Exception e){
            throw new Exception("LoginCommand: command was on different format, please, make sure to set the LoginCommandModel.");
        }

        /** Get chat **/
        ChatResult getChatResult = new ChatResult();
        ArrayList<Tuple> chat = chatDatabase.getChat(commandModel.getGameName());

        /** Prepare Result **/
        getChatResult.setSuccess(true);
        getChatResult.setData(chat);
        getChatResult.setMessage("Successfully got chat.");
        return getChatResult;
    }
}
