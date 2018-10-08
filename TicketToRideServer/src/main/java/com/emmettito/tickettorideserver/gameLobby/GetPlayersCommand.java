package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GetPlayersResult;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;
import java.util.ArrayList;

public class GetPlayersCommand implements IGameLobbyCommand {
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
        Game targetGame = gameLobbyDatabase.getGame(gameName);

        if (targetGame.equals(null)) {
            throw new Exception("Game does not exist on database.");
        }

        /** Get players **/
        ArrayList<Player> players = targetGame.getPlayers();
        if(players == null){
            players = new ArrayList<Player>();
        }

        /** Prepare Result **/
        GetPlayersResult getPlayersResult = new GetPlayersResult();
        getPlayersResult.setSuccess(true);
        getPlayersResult.setData(players);
        return getPlayersResult;

    }
}
