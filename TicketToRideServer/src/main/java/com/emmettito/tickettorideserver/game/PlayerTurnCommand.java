package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.CommandModels.GameCommands.PlayerTurnRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class PlayerTurnCommand implements IGameCommand{
    PlayerTurnRequest commandModel;

    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (PlayerTurnRequest)new Serializer().deserialize((InputStream)obj, PlayerTurnRequest.class);
        }catch (Exception e){
            throw new Exception("PlayerTurnCommand: command was null, please, make sure to set the PlayerTurnCommandModel.");
        }

        /** Validate **/
        /*if(!userIMA.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }*/

        Game game = gameIMA.getGame(commandModel.getGameName());
        Player player = gameIMA.getPlayer(commandModel.getGameName(), commandModel.getPlayerName());

        if (game == null){
            throw new Exception("Invalid game name.");
        }
        if (player == null){
            throw new Exception("Invalid player name.");
        }

        /*if(!game.isPlayerTurn(player)){
            throw new Exception("It is not your turn yet!");
        }*/

        int newPosition = gameIMA.incrementTurn(commandModel.getGameName());

        Result result = new Result(true, newPosition);

        // Add to command list
        String description = "This player ended his turn.";
        String requestJson = new Serializer().serialize(commandModel);
        String resultJson = new Serializer().serialize(result);
        Command command = new Command(commandModel.getPlayerName(), "PlayerEndTurn", description, requestJson, resultJson);
        gameIMA.addCommand(commandModel.getGameName(), command);

        return result;
    }
}
