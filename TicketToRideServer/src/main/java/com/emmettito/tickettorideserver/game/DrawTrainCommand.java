package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.DrawTrainRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Results.DrawTrainResult;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.DeckDao;

import java.io.InputStream;

public class DrawTrainCommand implements IGameCommand{
    DrawTrainRequest commandModel;
    DeckDao deckDatabase;

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

        result.setData(deckDatabase.getTrainCard(commandModel.getGameName()));
        if(deckDatabase.removeTrainCard(commandModel.getGameName(), result.getData()) == null){
            throw new Exception("Unable to remove card.");
        }


        /** Prepare Result **/
        result.setSuccess(true);
        result.setMessage("Successfully draw dest card.");
        return result;

    }
}
