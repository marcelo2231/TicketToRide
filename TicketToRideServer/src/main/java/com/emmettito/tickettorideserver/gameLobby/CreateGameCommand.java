package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.io.InputStream;

public class CreateGameCommand implements IGameLobbyCommand{
    /** Variables **/
    CreateGameRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (CreateGameRequest)new Serializer().deserialize((InputStream)obj, CreateGameRequest.class);
        }catch (Exception e){
            throw new Exception("CreateGameCommand: command was null, please, make sure to set the CreateGameCommandModel.");
        }

        /** Create game and player variable **/
        Game newGame = new Game();
        newGame.setGameName(commandModel.getGameName());

        Player newPlayer = new Player(commandModel.getUsername());

        /** Store data on Database **/
        try{
            gameLobbyDatabase.addGame(newGame);
            gameDatabase.addPlayer(newGame.getGameName(), newPlayer);
        }catch(DuplicateName e){
            throw new Exception("Game name already exists. Unable to add to database.");
        }

        // Result Returns a Player for the user
        return new Result();
    }
}
