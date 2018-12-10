package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.CommandModels.GameCommands.GetCommandsRequest;
import com.emmettito.models.Results.GetCommandsResult;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;
import java.util.ArrayList;

public class GetCommandsCommand implements IGameCommand {
    GetCommandsRequest commandModel;

    @Override
    public GetCommandsResult execute(Object obj, String authToken) throws Exception {
        /** Validate **/
        try {
            commandModel = (GetCommandsRequest) new Serializer().deserialize((InputStream) obj, GetCommandsRequest.class);
        } catch (Exception e) {
            throw new Exception("LoginCommand: command was on different format, please, make sure to set the LoginCommandModel.");
        }

        /** Get chat **/
        GetCommandsResult result = new GetCommandsResult();
        ArrayList<Command> data = gameIMA.getCommands(commandModel.getGameName(), commandModel.getCurrIndex());

        /** Prepare Result **/
        result.setSuccess(true);
        result.setData(data);
        result.setMessage("Successfully got commands.");
        return result;
    }
}
