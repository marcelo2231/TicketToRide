package com.emmettito.tickettorideserver.user;

import com.emmettito.models.CommandModels.UserCommand;
import com.emmettito.models.Results.Result;

public interface IUserCommand {
    Result execute(UserCommand obj) throws Exception ;
}
