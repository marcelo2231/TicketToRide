package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.StartGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettorideserver.communication.Serializer;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.io.InputStream;

public class StartGameCommand implements IGameLobbyCommand {
    StartGameRequest commandModel;

    @Override
    public Object execute(Object obj, String authToken) throws Exception {
        /** Validate **/
        try {
            commandModel = (StartGameRequest)new Serializer().deserialize((InputStream)obj, StartGameRequest.class);
        }catch(Exception e){
            throw new Exception("StartGameCommand: command was null, please, make sure to set the StartGameCommandModel.");
        }
        if(!userDatabase.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        /** Get Game **/
        Game currGame = gameLobbyDatabase.getGame(commandModel.getGameName());
        if (currGame == null) {
            throw new Exception("Unable to find game.");
        }

        /** Move to active game **/
        gameLobbyDatabase.removeGame(currGame.getGameName());
        gameLobbyDatabase.addActiveGame(currGame);

        /** Prepare Result **/
        GameLobbyResult gameLobbyResult = new GameLobbyResult();
        gameLobbyResult.setSuccess(true);
        gameLobbyResult.setMessage("Game started successfully.");
        return gameLobbyResult;
    }
}
