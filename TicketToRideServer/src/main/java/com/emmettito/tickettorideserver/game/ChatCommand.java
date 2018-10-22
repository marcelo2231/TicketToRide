package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.ChatRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.ChatDao;

import java.io.InputStream;

public class ChatCommand implements IGameCommand {
    ChatRequest commandModel;
    ChatDao chatDatabase;
    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (ChatRequest)new Serializer().deserialize((InputStream)obj, ChatRequest.class);
        }catch (Exception e){
            throw new Exception("DrawDestCardCommand: command was null, please, make sure to set the DrawDestCardCommandModel.");
        }

        /** Validate **/
        if(!userDatabase.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        /** Draw card **/
        Result result = new Result();

        chatDatabase.addToChat(commandModel.getGameName(), commandModel.getPlayerName(), commandModel.getMessage());

        /** Prepare Result **/
        result.setSuccess(true);
        result.setData("data");
        result.setMessage("Successfully draw dest card.");
        return result;
    }
}
