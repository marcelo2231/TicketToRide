package com.emmettito.tickettorideserver.user;

import com.emmettito.models.CommandModels.UserCommand;
import com.emmettito.models.Result;

public interface IUserCommand {
    Result execute(UserCommand obj) throws Exception ;
}
