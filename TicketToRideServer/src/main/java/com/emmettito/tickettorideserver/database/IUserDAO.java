package com.emmettito.tickettorideserver.database;

import com.emmettito.models.User;

public interface IUserDAO {
    boolean addUser(String username, String password);
    String generateAuthToken(String username);
    boolean checkAuthToken(String authToken);
    String serialize(User user);
    User deserialize(String userString);
}
