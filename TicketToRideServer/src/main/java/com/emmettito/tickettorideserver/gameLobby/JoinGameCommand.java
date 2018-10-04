package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.AuthToken;
import com.emmettito.models.CommandModels.GameLobbyCommands.JoinGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.GameLobbyDao;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

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
            throw new Exception("Game name is null or empty. Please, do not forget to set all variables.");
        }
        if(commandModel.getUsername() == null || commandModel.getUsername().isEmpty()){
            throw new Exception("Username is null or empty. Please, do not forget to set all variables.");
        }

        /** Creating a player and adding it to the game **/
        GameLobbyResult result = new GameLobbyResult();
        Player newPlayer = new Player(commandModel.getUsername());

        try {
            gameDatabase.addPlayer(commandModel.getGameName(), newPlayer);
            result.setRenewedAuthToken(gameDatabase.generateAuthToken(commandModel.getUsername()).getAuthToken());
        }catch(Exception e){
            throw e;
        }

        // Result Returns a Player for the user
        result.setSuccess(true);
        result.setData(newPlayer);
        result.setMessage("Successfully joined game.");
        return result;
    }
}
