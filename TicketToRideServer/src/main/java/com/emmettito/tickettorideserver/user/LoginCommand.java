package com.emmettito.tickettorideserver.user;

import com.emmettito.models.AuthToken;
import com.emmettito.models.CommandModels.UserCommands.LoginRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.models.User;
import com.emmettito.tickettorideserver.communication.Serializer;

import org.omg.PortableInterceptor.ORBInitInfoPackage.InvalidName;

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

        /** Create User Object **/
        User user = new User(commandModel.getUsername(), commandModel.getPassword());
        AuthToken resultAuthToken;

        /** Add User to Database **/
        try {
            resultAuthToken = userDatabase.loginUser(user);
        }catch(InvalidName e){
            throw new Exception("Username or Password is Invalid. Login Failed.");
        }

        /** Prepare Result **/

        return new Result();
    }
}
