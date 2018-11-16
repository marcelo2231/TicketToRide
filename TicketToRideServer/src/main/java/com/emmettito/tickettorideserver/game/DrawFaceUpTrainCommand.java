package com.emmettito.tickettorideserver.game;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.CommandModels.GameCommands.DrawFaceUpTrainRequest;
import com.emmettito.models.Results.DrawTrainResult;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.DeckDao;

import java.io.InputStream;

public class DrawFaceUpTrainCommand  implements IGameCommand{
    DrawFaceUpTrainRequest commandModel;
    DeckDao deckDatabase = new DeckDao();

    @Override
    public DrawTrainResult execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (DrawFaceUpTrainRequest)new Serializer().deserialize((InputStream)obj, DrawFaceUpTrainRequest.class);
        }catch(Exception e){
            throw new Exception("DrawTrainCommand: command was null, please, make sure to set the DrawTrainCommandModel.");
        }
        /** Validate **/
        if(!userDatabase.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }
        if(commandModel.getCardIndex() < 0 || commandModel.getCardIndex() > 4){
            throw new Exception("Invalid card index. The card index must be a number between 0 and 4");
        }

        /** Draw card **/
        DrawTrainResult result = new DrawTrainResult();
        TrainCard card = deckDatabase.removeFaceUpTrainCardFromDeck(commandModel.getGameName(), commandModel.getCardIndex());
        //deckDatabase.addTrainCardToPlayer(commandModel.getGameName(), commandModel.getPlayerName(), card);

        if(card == null){
            throw new Exception("Unable to draw card");
        }

        /** Prepare Result **/
        result.setSuccess(true);
        result.setData(card);
        result.setMessage("Successfully drew train card.");

        // Add to command list
        String description = "Drew train card with id " + card.getCardID() + " and color " + card.getColor().toString() + ".";
        String requestJson = new Serializer().serialize(commandModel);
        String resultJson = new Serializer().serialize(result);
        Command command = new Command(commandModel.getPlayerName(), "DrawFaceUpTrainCard", description, requestJson, resultJson);
        gameDatabase.addCommand(commandModel.getGameName(), command);
        return result;
    }
}
