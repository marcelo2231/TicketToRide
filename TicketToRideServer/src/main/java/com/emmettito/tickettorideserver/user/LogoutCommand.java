package com.emmettito.tickettorideserver.user;

import com.emmettito.models.CommandModels.UserCommand;
import com.emmettito.models.CommandModels.UserCommands.LogoutCommandModel;
import com.emmettito.models.Results.Result;

public class LogoutCommand implements IUserCommand{
    LogoutCommandModel commandModel;

    @Override
    public Result execute(UserCommand obj) throws Exception {
        if(obj.getLogoutCommandModel() != null) {
            commandModel = obj.getLogoutCommandModel();
        }else{
            throw new Exception("LogoutCommand: command was null, please, make sure to set the LogoutCommandModel.");
        }

        // TODO: Store data on Database

        return new Result();
    }
}
