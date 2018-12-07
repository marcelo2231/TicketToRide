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

        /** Validate **/
        if(user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword().isEmpty() || user.getPassword() == null){
            throw new Exception("Username or password empty. Please, do not forget to fill out all fields.");
        }

        /** Add User to InternalMemory **/
        if(!userDatabase.compareUserAndPassword(user)){
            throw new Exception("Username or Password is Invalid. Login Failed.");
        }
        resultAuthToken = userDatabase.generateAuthToken(user.getUsername());

        /** Prepare Result **/
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("Logged in Successfully.");
        result.setData(resultAuthToken.getAuthToken());
        return result;
    }
}
