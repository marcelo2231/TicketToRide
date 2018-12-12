package com.emmettito.tickettorideserver.game;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.CommandModels.GameCommands.DrawDestCardRequest;
import com.emmettito.models.Results.DrawDestCardResult;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.DeckIMA;

import java.io.InputStream;

public class DrawDestCardCommand implements IGameCommand{
    DrawDestCardRequest commandModel;
    DeckIMA deckDatabase = new DeckIMA();

    @Override
    public DrawDestCardResult execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            System.out.println(obj);
            System.out.println(obj.getClass());
            commandModel = (DrawDestCardRequest)new Serializer().deserialize((InputStream)obj, DrawDestCardRequest.class);
            System.out.println(commandModel);
        }catch (Exception e){
            System.out.println("There");
            throw new Exception("DrawDestCardCommand: command was null, please, make sure to set the DrawDestCardCommandModel.");
        }

        /** Validate **/
        /*if(!userIMA.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }*/

        System.out.println(deckDatabase);
        System.out.println(commandModel);

        /** Draw card **/
        DrawDestCardResult result = new DrawDestCardResult();
        DestinationCard card = deckDatabase.removeTopDestCardFromDeck(commandModel.getGameName());
        deckDatabase.addDestCardToPlayer(commandModel.getGameName(), commandModel.getPlayerName(), card);

        System.out.println(card);

        if(card == null){
            throw new Exception("Unable to draw card");
        }

        /** Prepare Result **/
        result.setSuccess(true);
        result.setData(card);
        result.setMessage("Successfully draw dest card.");

        return result;
    }
}
