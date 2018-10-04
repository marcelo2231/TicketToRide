package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettorideserver.communication.Serializer;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.io.InputStream;

public class CreateGameCommand implements IGameLobbyCommand{
    /** Variables **/
    CreateGameRequest commandModel;

    @Override
    public GameLobbyResult execute(Object obj, String authToken) throws Exception {
        /** Cast Object and Validate AuthToken**/
        try {
            commandModel = (CreateGameRequest)new Serializer().deserialize((InputStream)obj, CreateGameRequest.class);
        }catch (Exception e){
            throw new Exception("CreateGameCommand: command was null, please, make sure to set the CreateGameCommandModel.");
        }
        /** Validate **/
        if(!gameDatabase.authTokenIsValid(authToken)){
            throw new Exception("Invalid authToken. You do not have authorization to execute this command.");
        }
        if(commandModel.getGameName() == null || commandModel.getGameName().isEmpty()){
            throw new Exception("Game name is null or empty. Please, do not forget to set all variables.");
        }
        if(!userDatabase.isValidUsername(commandModel.getUsername())){
            throw new Exception("Username is invalid, there is no user with that username.");
        }

        /** Create game and player variable **/
        Game newGame = new Game();
        GameLobbyResult result = new GameLobbyResult();
        newGame.setGameName(commandModel.getGameName());

        Player newPlayer = new Player(commandModel.getUsername());

        /** Store data on Database **/
        try{
            gameLobbyDatabase.addGame(newGame);
            gameDatabase.addPlayer(newGame.getGameName(), newPlayer);
            result.setRenewedAuthToken(gameDatabase.generateAuthToken(commandModel.getUsername()).getAuthToken());
        }catch(DuplicateName e){
            throw new Exception("Game name already exists. Unable to add to database.");
        }catch(Exception e){
            throw e;
        }

        // Result Returns a Player for the user
        result.setSuccess(true);
        result.setData(newPlayer);
        result.setMessage("Successfully created game.");
        return result;
    }
}
