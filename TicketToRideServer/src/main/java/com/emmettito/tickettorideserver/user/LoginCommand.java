package com.emmettito.tickettorideserver.user;

import com.emmettito.models.CommandModels.UserCommand;
import com.emmettito.models.CommandModels.UserCommands.LoginCommandModel;
import com.emmettito.models.Result;

public class LoginCommand implements IUserCommand{
    LoginCommandModel commandModel;

    @Override
    public Result execute(UserCommand obj) throws Exception {
        if(obj.getLoginCommandModel() != null) {
            commandModel = obj.getLoginCommandModel();
        }else{
            throw new Exception("LoginCommand: command was null, please, make sure to set the LoginCommandModel.");
        }

        return new Result();
    }
}
