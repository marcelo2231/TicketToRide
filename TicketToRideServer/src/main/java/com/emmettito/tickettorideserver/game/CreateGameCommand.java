package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettorideserver.communication.Serializer;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.io.InputStream;

public class CreateGameCommand implements IGameCommand{
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
        if(!userDao.authTokenAndUserAreValid(authToken, commandModel.getUsername())){
            throw new Exception("Invalid authToken or username. You do not have authorization to execute this command.");
        }
        if(commandModel.getGameName() == null || commandModel.getGameName().isEmpty()){
            throw new Exception("Game name is null or empty. Please, do not forget to set all variables.");
        }

        /** Create game and player variable **/
        Game newGame = new Game();
        GameLobbyResult result = new GameLobbyResult();
        newGame.setGameName(commandModel.getGameName());

        Player newPlayer = new Player(commandModel.getUsername(), 0);

        /** Store data on Database **/
        try{
            gameDao.addGame(newGame);
            gameDao.addPlayer(newGame.getGameName(), newPlayer);
            result.setRenewedAuthToken(userDao.generateAuthToken(commandModel.getUsername()).getAuthToken());
        }catch(DuplicateName e){
            throw new Exception("Game name already exists. Unable to add to database.");
        }catch(Exception e){
            throw e;
        }

        /** Prepare Result **/
        result.setSuccess(true);
        result.setData(newPlayer);
        result.setMessage("Successfully created game.");
        return result;
    }
}
