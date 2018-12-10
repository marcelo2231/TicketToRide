package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommands.GetGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Results.GetGameResult;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class GetGameCommand implements IGameCommand{
    GetGameRequest commandModel;
    @Override
    public GetGameResult execute(Object obj, String authToken) throws Exception {
        /** Validate **/
        try {
            commandModel = (GetGameRequest)new Serializer().deserialize((InputStream)obj, GetGameRequest.class);
        }catch(Exception e){
            throw new Exception("StartGameCommand: command was null, please, make sure to set the StartGameCommandModel.");
        }

        /** Get list of games **/
        Game game = gameIMA.getGame(commandModel.getGameName());

        if (game == null) { throw new Exception("Game not found"); }

        /** Prepare Result **/
        GetGameResult result = new GetGameResult();
        result.setSuccess(true);
        result.setData(game);
        result.setMessage("Game returned.");
        return result;
    }
}
