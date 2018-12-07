package com.emmettito.tickettorideserver.user;

import com.emmettito.models.AuthToken;
import com.emmettito.models.CommandModels.UserCommands.RegisterRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.models.User;
import com.emmettito.tickettorideserver.communication.Serializer;

import java.io.InputStream;

public class RegisterCommand implements IUserCommand{
    /** Variables **/
    RegisterRequest commandModel;

    @Override
    public Result execute(Object obj) throws Exception {
        /** Cast Object **/
        try{
            commandModel = (RegisterRequest)new Serializer().deserialize((InputStream)obj, RegisterRequest.class);
        }catch (Exception e){
            throw new Exception("RegisterCommand: command was null, please, make sure to set the RegisterCommandModel.");
        }

        /** Create User Object **/
        User newUser = new User(commandModel.getUsername(), commandModel.getPassword());
        AuthToken resultAuthToken;

        /** Validate Username **/
        if(newUser.getUsername() == null || newUser.getUsername().isEmpty() ||
                newUser.getPassword().isEmpty() || newUser.getPassword() == null){
            throw new Exception("Username or password empty. Please, do not forget to fill out all fields.");
        }

        if(newUser.getUsername().length() < 6 || newUser.getUsername().length() > 12){
            throw new Exception("Invalid username. Username length must be between 6 to 12 digits.");
        }

        for (char c : newUser.getUsername().toCharArray()) {
            if (Character.isWhitespace(c)) {
                throw new Exception("Invalid username. Username must not have white space.");
            }
        }

        /** Validate Password **/
        if(newUser.getPassword().length() < 2 || newUser.getPassword().length() > 12){
            throw new Exception("Invalid password. Password length must be between 2 to 12 digits.");
        }

        for (char c : newUser.getPassword().toCharArray()) {
            if (Character.isWhitespace(c)) {
                throw new Exception("Invalid password. Password must not have white space.");
            }
        }


        /** Add User to InternalMemory **/
        if(userDatabase.getUser(newUser.getUsername()) != null) {
            throw new Exception("Username already exists. Unable to add to database.");
        }
        if(!userDatabase.addUser(newUser)){
            throw new Exception("Unable to add user to database.");
        }

        /** Generate new Auth Token **/
        resultAuthToken = userDatabase.generateAuthToken(newUser.getUsername());

        /** Prepare Result **/
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("Registered Successfully.");
        result.setData(resultAuthToken.getAuthToken());
        return result;
    }
}
