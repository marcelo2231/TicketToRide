package com.emmettito.tickettorideserver.user;

import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.database.GameDao;
import com.emmettito.tickettorideserver.database.GameLobbyDao;
import com.emmettito.tickettorideserver.database.UserDao;

public interface IUserCommand {
    UserDao userDatabase = new UserDao();
    Result execute(Object obj) throws Exception ;
}
