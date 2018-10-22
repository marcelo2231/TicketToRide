package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.DrawDestCardRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Results.DrawDestCardResult;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class DrawDestCardCommand implements IGameCommand{
    DrawDestCardRequest commandModel;
    @Override
    public DrawDestCardResult execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (DrawDestCardRequest)new Serializer().deserialize((InputStream)obj, DrawDestCardRequest.class);
        }catch (Exception e){
            throw new Exception("DrawDestCardCommand: command was null, please, make sure to set the DrawDestCardCommandModel.");
        }

        /** Validate **/
        if(!userDatabase.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        /** Draw card **/
        DrawDestCardResult result = new DrawDestCardResult();
        Game game = gameLobbyDatabase.getGame(commandModel.getGameName());


        /** Prepare Result **/
        result.setSuccess(true);
        result.setData("data");
        result.setMessage("Successfully draw dest card.");
        return result;
    }
}
