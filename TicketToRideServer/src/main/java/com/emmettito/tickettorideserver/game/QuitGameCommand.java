package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.QuitGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;
import java.util.ArrayList;

public class QuitGameCommand implements IGameCommand {
    QuitGameRequest commandModel;

    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Validate **/
        try {
            commandModel = (QuitGameRequest)new Serializer().deserialize((InputStream)obj, QuitGameRequest.class);
        }catch(Exception e){
            throw new Exception("ExitGameCommand: command was null, please, make sure to set the QuitGameCommandModel.");
        }
        if(!userIMA.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        /** Get Game **/
        Game targetGame = gameIMA.getGame(commandModel.getGameName());
        if (targetGame == null) {
            throw new Exception("Error, game not found");
        }

        /** Remove Player from Game **/
        if (!targetGame.playerInGame(commandModel.getPlayerName())) {
            throw new Exception("Player is not in game.");
        }
        else {
            targetGame.removePlayer(commandModel.getPlayerName());
            ArrayList<Player> players = targetGame.getPlayers();

            /*for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                player.setPosition(i + 1);
                player.setColor(PlayerColor.values()[player.getPosition()]);
                players.set(i, player);
            }

            targetGame.setPlayers(players);*/

            gameIMA.setGame(targetGame);
        }

        /** Prepare Result **/
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("Successfully left game.");
        return result;
    }
}
