package com.emmettito.tickettorideserver.game;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.CommandModels.GameCommands.DrawDestCardRequest;
import com.emmettito.models.Results.DrawDestCardResult;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.DeckDao;

import java.io.InputStream;

public class DrawDestCardCommand implements IGameCommand{
    DrawDestCardRequest commandModel;
    DeckDao deckDatabase = new DeckDao();
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
        DestinationCard card = deckDatabase.removeTopDestCardFromDeck(commandModel.getGameName());
        deckDatabase.addDestCardToPlayer(commandModel.getGameName(), commandModel.getPlayerName(), card);

        if(card == null){
            throw new Exception("Unable to draw card");
        }

        /** Prepare Result **/
        result.setSuccess(true);
        result.setData(card);
        result.setMessage("Successfully draw dest card.");

        // Add to command list
        String serializedRequest = new Serializer().serialize(commandModel);
        String serializedResult = new Serializer().serialize(result);
        gameDatabase.addCommand(commandModel.getGameName(), this.getClass(), serializedRequest, serializedResult);
        return result;
    }
}
