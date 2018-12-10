package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GetPlayersResult;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;
import java.util.ArrayList;

public class GetPlayersCommand implements IGameCommand {
    GetPlayersRequest commandModel;

    @Override
    public Object execute(Object obj, String authToken) throws Exception {
        /** Validate **/
        try{
            commandModel = (GetPlayersRequest)new Serializer().deserialize((InputStream)obj, GetPlayersRequest.class);
        }catch(Exception e){
            throw new Exception("LoginCommand: command was on different format, please, make sure to set the LoginCommandModel.");
        }
        /** Validate **/

        /** Get game and validate **/
        String gameName = commandModel.getGameName();
        Game targetGame = gameIMA.getGame(gameName);
        GetPlayersResult getPlayersResult = new GetPlayersResult();

        if (targetGame.equals(null)) {
            throw new Exception("Game does not exist on database.");
        }

        System.out.println("Has the game started: " + targetGame.isStarted());

        /** Check if game started **/
        Game game = gameIMA.getGame(commandModel.getGameName());

        System.out.println("How about now: " + game.isStarted());

        /*if (game == null){
            // Game have not started, do nothing
        }else{
            //getPlayersResult.setDidGameStart(true);
        }*/

        if (game.isStarted()) {
            getPlayersResult.setDidGameStart(true);
        }

        /** Get players **/
        ArrayList<Player> players = targetGame.getPlayers();
        if(players == null){
            players = new ArrayList<Player>();
        }

        /** Prepare Result **/
        getPlayersResult.setSuccess(true);
        getPlayersResult.setData(players);
        return getPlayersResult;
    }
}
