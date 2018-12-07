package com.emmettito.tickettorideserver.game;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.CommandModels.GameCommands.DrawTrainRequest;
import com.emmettito.models.Results.DrawTrainResult;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.DeckIMA;

import java.io.InputStream;

public class DrawTrainCommand implements IGameCommand{
    DrawTrainRequest commandModel;
    DeckIMA deckDatabase = new DeckIMA();

    @Override
    public DrawTrainResult execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (DrawTrainRequest)new Serializer().deserialize((InputStream)obj, DrawTrainRequest.class);
        }catch(Exception e){
            throw new Exception("DrawTrainCommand: command was null, please, make sure to set the DrawTrainCommandModel.");
        }
        /** Validate **/
        if(!userDao.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        /** Draw card **/
        DrawTrainResult result = new DrawTrainResult();
        TrainCard card = deckDatabase.removeTopTrainCardFromDeck(commandModel.getGameName());
        //deckDatabase.addTrainCardToPlayer(commandModel.getGameName(), commandModel.getPlayerName(), card);

        if(card == null){
            throw new Exception("Unable to draw card");
        }

        /** Prepare Result **/
        result.setSuccess(true);
        result.setData(card);
        result.setMessage("Successfully draw train card.");

        // Add to command list
        String description = "Drew train card with id " + card.getCardID() + " and color " + card.getColor().toString() + ".";
        String requestJson = new Serializer().serialize(commandModel);
        String resultJson = new Serializer().serialize(result);
        Command command = new Command(commandModel.getPlayerName(), "DrawTrainCard", description, requestJson, resultJson);
        gameDao.addCommand(commandModel.getGameName(), command);
        return result;
    }
}
