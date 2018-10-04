package com.emmettito.tickettorideserver.user;

import com.emmettito.models.CommandModels.UserCommands.LogoutRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;
import org.omg.PortableInterceptor.ORBInitInfoPackage.InvalidName;
import java.io.InputStream;

public class LogoutCommand implements IUserCommand{
    LogoutRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        /** Cast Object **/
        try{
            commandModel = (LogoutRequest)new Serializer().deserialize((InputStream)obj, LogoutRequest.class);
        }catch(Exception e){
            throw new Exception("LogoutCommand: command was null, please, make sure to set the LogoutCommandModel.");
        }

        /** Validate **/
        if(commandModel.getUsername() == null || commandModel.getUsername().isEmpty()){
            throw new Exception("Username null or empty. Please, do not forget to fill out all fields.");
        }

        /** Add User to Database **/
        if(!userDatabase.removeAuthToken(commandModel.getUsername())){
            throw new Exception("No user logged in with requested username. Logout failed.");
        }

        /** Prepare Result **/
        return new Result(true, "Logout successfully.");
    }
}
