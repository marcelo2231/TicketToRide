package com.emmettito.tickettorideserver.database;

import com.emmettito.models.AuthToken;
import com.emmettito.models.User;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

public class UserDao {
    /** Database Instance **/
    private static Database dbInstance = Database.getInstance();

    /** Methods **/
    public AuthToken registerUser(User user) throws DuplicateName{



        AuthToken newAuthToken = new AuthToken();

        return newAuthToken;
    }
}
