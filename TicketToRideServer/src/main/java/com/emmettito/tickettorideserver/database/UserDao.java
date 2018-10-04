package com.emmettito.tickettorideserver.database;

import com.emmettito.models.AuthToken;
import com.emmettito.models.User;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.omg.PortableInterceptor.ORBInitInfoPackage.InvalidName;

public class UserDao {
    /** Database Instance **/
    private static Database dbInstance = Database.getInstance();

    /** Methods **/
    public AuthToken registerUser(User newUser) throws DuplicateName{

        if (dbInstance.userExists(newUser.getUsername())) {
            throw new DuplicateName();
        }
        else {
            dbInstance.users.add(newUser);
        }

        return generateAuthToken(newUser.getUsername());
    }

    public AuthToken loginUser(User user) throws InvalidName {

        if (!dbInstance.loginValidation(user)) {
            throw new InvalidName();
        }

        return generateAuthToken(user.getUsername());
    }

    public void logoutUser(String username) throws InvalidName {

        if (!dbInstance.removeAuthToken(username)) {
            throw new InvalidName();
        }

        return;
    }

    public AuthToken generateAuthToken(String username){
        return dbInstance.addAuthToken(username);
    }
}
