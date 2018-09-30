package com.emmettito.tickettorideserver.game;

import com.emmettito.models.CommandModels.GameCommand;
import com.emmettito.models.CommandModels.GameCommands.ClaimRouteCommandModel;
import com.emmettito.models.Results.Result;

public class ClaimRouteCommand implements IGameCommand{
    ClaimRouteCommandModel commandModel;

    @Override
    public Result execute(GameCommand obj) throws Exception {
        if(obj.getClaimRouteCommandModel() != null) {
            commandModel = obj.getClaimRouteCommandModel();
        }else{
            throw new Exception("ClaimRouteCommand: command was null, please, make sure to set the ClaimRouteCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
