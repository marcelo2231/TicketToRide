package com.emmettito.tickettorideserver.user;

import com.emmettito.models.CommandModels.UserCommand;
import com.emmettito.models.CommandModels.UserCommands.RegisterCommandModel;
import com.emmettito.models.Results.Result;

public class RegisterCommand implements IUserCommand{
    RegisterCommandModel commandModel;

    @Override
    public Result execute(UserCommand obj) throws Exception {
        if(obj.getRegisterCommandModel() != null) {
            commandModel = obj.getRegisterCommandModel();
        }else{
            throw new Exception("RegisterCommand: command was null, please, make sure to set the RegisterCommandModel.");
        }

        // TODO: Store data on Database

        // Result Returns am AuthToken
        return new Result();
    }
}
