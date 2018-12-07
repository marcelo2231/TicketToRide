package com.emmettito.tickettorideserver.user;

import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.database.UserIMA;

public interface IUserCommand {
    UserIMA userDatabase = new UserIMA();
    Result execute(Object obj) throws Exception ;
}
