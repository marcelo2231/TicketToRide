package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.DrawTrainRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Results.DrawTrainResult;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class DrawTrainCommand implements IGameCommand{
    DrawTrainRequest commandModel;

    @Override
    public DrawTrainResult execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (DrawTrainRequest)new Serializer().deserialize((InputStream)obj, DrawTrainRequest.class);
        }catch(Exception e){
            throw new Exception("DrawTrainCommand: command was null, please, make sure to set the DrawTrainCommandModel.");
        }
        /** Validate **/
        if(!userDatabase.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        /** Draw card **/
        DrawTrainResult result = new DrawTrainResult();
        Game game = gameLobbyDatabase.getGame(commandModel.getGameName());


        /** Prepare Result **/
        result.setSuccess(true);
        result.setData("data");
        result.setMessage("Successfully draw dest card.");
        return result;

    }
}
