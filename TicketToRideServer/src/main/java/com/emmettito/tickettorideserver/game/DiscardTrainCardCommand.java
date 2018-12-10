package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.CommandModels.GameCommands.DiscardCardRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.DeckIMA;

import java.io.InputStream;

public class DiscardTrainCardCommand implements IGameCommand {
    DiscardCardRequest commandModel;
    DeckIMA deckDatabase = new DeckIMA();

    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (DiscardCardRequest) new Serializer().deserialize((InputStream) obj, DiscardCardRequest.class);
        } catch (Exception e) {
            throw new Exception("DiscardTrainCardCommand: command was null, please, make sure to set the DrawTrainCommandModel.");
        }
        /** Validate **/
        if (!userIMA.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())) {
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        /** Draw card **/
        Result result = new Result();
        deckDatabase.removeTrainCardFromPlayer(commandModel.getGameName(), commandModel.getPlayerName(), commandModel.getCardID());

        /** Prepare Result **/
        result.setSuccess(true);
        result.setMessage("Successfully discarded train card.");

        // Add to command list
        String description = "Train card (ID: " + commandModel.getCardID() + ") was discarded.";
        String requestJson = new Serializer().serialize(commandModel);
        String resultJson = new Serializer().serialize(result);
        Command command = new Command(commandModel.getPlayerName(), "DiscardTrainCard", description, requestJson, resultJson);
        gameIMA.addCommand(commandModel.getGameName(), command);
        return result;
    }
}
