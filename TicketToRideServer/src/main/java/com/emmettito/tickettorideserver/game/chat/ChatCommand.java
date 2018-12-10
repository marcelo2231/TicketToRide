package com.emmettito.tickettorideserver.game.chat;

import com.emmettito.models.CommandModels.GameCommands.ChatRequest;
import com.emmettito.models.Results.ChatResult;
import com.emmettito.models.Tuple;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.ChatIMA;
import com.emmettito.tickettorideserver.game.IGameCommand;

import java.io.InputStream;
import java.util.ArrayList;

public class ChatCommand implements IGameCommand {
    ChatRequest commandModel;
    ChatIMA chatDatabase = new ChatIMA();
    @Override
    public ChatResult execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (ChatRequest)new Serializer().deserialize((InputStream)obj, ChatRequest.class);
        }catch (Exception e){
            throw new Exception("DrawDestCardCommand: command was null, please, make sure to set the DrawDestCardCommandModel.");
        }

        /** Validate **/
        if(!userIMA.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        /** Add to chat **/
        ChatResult result = new ChatResult();

        chatDatabase.addToChat(commandModel.getGameName(), commandModel.getPlayerName(), commandModel.getMessage());
        ArrayList<Tuple> chat = chatDatabase.getChat(commandModel.getGameName());

        /** Prepare Result **/
        result.setSuccess(true);
        result.setData(chat);
        result.setMessage("Successfully added a message to chat.");
        return result;
    }
}
