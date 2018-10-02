package com.emmettito.tickettorideserver.user;

import com.emmettito.models.Results.Result;

public interface IUserCommand {
    Result execute(Object obj) throws Exception ;
}
