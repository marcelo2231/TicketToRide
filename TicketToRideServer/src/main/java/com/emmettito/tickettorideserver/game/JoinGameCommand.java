package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameLobbyCommands.JoinGameRequest;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettorideserver.communication.Serializer;


import java.io.InputStream;
import java.util.ArrayList;

public class JoinGameCommand implements IGameCommand{
    JoinGameRequest commandModel;

    @Override
    public GameLobbyResult execute(Object obj, String authToken) throws Exception {
        /** Cast Object **/
        try {
            commandModel = (JoinGameRequest)new Serializer().deserialize((InputStream)obj, JoinGameRequest.class);
        }catch (Exception e){
            throw new Exception("JoinGameCommand: command was null, please, make sure to set the JoinGameCommandModel.");
        }

        /** Validate **/
        if(!userDao.authTokenAndUserAreValid(authToken, commandModel.getUsername())){
            throw new Exception("Invalid authToken or username. You do not have authorization to execute this command.");
        }

        /** Creating a player and adding it to the game **/
        GameLobbyResult result = new GameLobbyResult();
        ArrayList<Player> players = gameDao.getPlayers(commandModel.getGameName());
        Player newPlayer = new Player(commandModel.getUsername(), players.size());

        try {
            gameDao.addPlayer(commandModel.getGameName(), newPlayer);
            result.setRenewedAuthToken(userDao.generateAuthToken(commandModel.getUsername()).getAuthToken());
        }catch(Exception e){
            throw e;
        }

        /** Prepare Result **/
        result.setSuccess(true);
        result.setData(newPlayer);
        result.setMessage("Successfully joined game.");
        return result;
    }
}
