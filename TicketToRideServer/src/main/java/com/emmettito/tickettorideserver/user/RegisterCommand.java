package com.emmettito.tickettorideserver.user;

import com.emmettito.models.CommandModels.UserCommand;
import com.emmettito.models.CommandModels.UserCommands.RegisterCommandModel;
import com.emmettito.models.Result;

public class RegisterCommand implements IUserCommand{
    RegisterCommandModel commandModel;

    @Override
    public Result execute(UserCommand obj) throws Exception {
        if(obj.getRegisterCommandModel() != null) {
            commandModel = obj.getRegisterCommandModel();
        }else{
            throw new Exception("RegisterCommand: command was null, please, make sure to set the RegisterCommandModel.");
        }

        return new Result();
    }
}
