package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.JoinGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class JoinGameCommand implements IGameLobbyCommand{
    JoinGameRequest commandModel;

    @Override
    public GameLobbyResult execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (JoinGameRequest)new Serializer().deserialize((InputStream)obj, JoinGameRequest.class);
        }catch (Exception e){
            throw new Exception("JoinGameCommand: command was null, please, make sure to set the JoinGameCommandModel.");
        }
        if(!gameDatabase.authTokenIsValid(authToken)){
            throw new Exception("Invalid authToken. You do not have authorization to execute this command.");
        }

        /** Validate **/
        if(!gameDatabase.authTokenIsValid(authToken)){
            throw new Exception("Invalid authToken. You do not have authorization to execute this command.");
        }
        if(commandModel.getGameName() == null || commandModel.getGameName().isEmpty()){
            throw new Exception("Game name is null or empty. Please, do not forget to fill out all fields.");
        }

        /** Creating a user and adding it to the game **/


        // TODO: Store data on Database

        // Result Returns a Player for the user
        return new GameLobbyResult();
    }
}
