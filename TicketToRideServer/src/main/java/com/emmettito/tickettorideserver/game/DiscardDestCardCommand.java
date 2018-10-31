package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.DiscardCardRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.DeckDao;

import java.io.InputStream;

public class DiscardDestCardCommand implements IGameCommand {
    DiscardCardRequest commandModel;
    DeckDao deckDatabase = new DeckDao();

    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (DiscardCardRequest) new Serializer().deserialize((InputStream) obj, DiscardCardRequest.class);
        } catch (Exception e) {
            throw new Exception("DiscardTrainCardCommand: command was null, please, make sure to set the DrawTrainCommandModel.");
        }
        /** Validate **/
        if (!userDatabase.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())) {
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        /** Draw card **/
        Result result = new Result();
        deckDatabase.removeTrainCardFromPlayer(commandModel.getGameName(), commandModel.getPlayerName(), commandModel.getCardID());

        /** Prepare Result **/
        result.setSuccess(true);
        result.setMessage("Successfully discarded train card.");

        String serializedRequest = new Serializer().serialize(commandModel);
        String serializedResult = new Serializer().serialize(result);
        gameDatabase.addCommand(commandModel.getGameName(), this.getClass(), serializedRequest, serializedResult);
        return result;
    }
}
