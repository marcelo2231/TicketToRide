package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.EndGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class EndGameCommand implements IGameCommand{
    EndGameRequest commandModel;

    @Override
    public Result execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (EndGameRequest)new Serializer().deserialize((InputStream)obj, EndGameRequest.class);
        }catch (Exception e){
            throw new Exception("EndGameCommand: command was null, please, make sure to set the EndGameCommandModel.");
        }
        if(!userIMA.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        // TODO: Store data on InternalMemory

        //gameLobbyDatabase.removeActiveGame(commandModel.getGameName());
        Game game = gameIMA.getGame(commandModel.getGameName());
        gameIMA.removeGame(game.getGameName());
        gameIMA.addEndedGame(game);


        return new Result();
    }
}
