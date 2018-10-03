package com.emmettito.tickettorideserver.user;

import com.emmettito.models.CommandModels.UserCommands.LoginRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class LoginCommand implements IUserCommand{
    /** Variables **/
    LoginRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        /** Cast Object **/
        try{
            commandModel = (LoginRequest)new Serializer().deserialize((InputStream)obj, LoginRequest.class);
        }catch(Exception e){
            throw new Exception("LoginCommand: command was on different format, please, make sure to set the LoginCommandModel.");
        }

        /** Validate Credentials **/


        /** Prepare Result **/

        return new Result();
    }
}
