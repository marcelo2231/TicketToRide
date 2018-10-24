package com.emmettito.tickettorideserver.game;

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
        if(!userDatabase.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        Game game = gameLobbyDatabase.getActiveGame(commandModel.getGameName());
        Player player = gameDatabase.getPlayer(commandModel.getGameName(), commandModel.getPlayerName());
        if (game == null){
            throw new Exception("Invalid game name.");
        }
        if (player == null){
            throw new Exception("Invalid player name.");
        }

        if(!game.isPlayerTurn(player)){
            throw new Exception("It is not your turn yet!");
        }

        // TODO: Store data on Database
        // call incrementTurnIndex() on game after players turn

        return new Result();
    }
}
