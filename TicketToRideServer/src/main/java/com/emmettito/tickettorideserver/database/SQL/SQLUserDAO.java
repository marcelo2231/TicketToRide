package com.emmettito.tickettorideserver.database.SQL;

import com.emmettito.models.AuthToken;
import com.emmettito.models.User;
import com.emmettito.tickettorideserver.database.IUserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDAO implements IUserDAO {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    public SQLUserDAO() {
        String driver = "org.sqlite.JDBC";

        try {
            Class.forName(driver);

            String dbname = "jdbc:sqlite:user.db";     //Name of the database file

            connection = DriverManager.getConnection(dbname);

            String create = "create table if not exists user (username text not null primary key, " +
                    "password text not null, auth_token text);";

            statement = connection.prepareStatement(create);
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addUser(String username, String password) {
        User userInDatabase = getUser(username);

        if (userInDatabase != null) {   //User already exists
            return false;
        }

        String authToken = generateAuthToken(username);

        try {
            String insert = "insert into user values (?,?,?)";

            statement = connection.prepareStatement(insert);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, authToken);
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public User getUser(String username) {
        User foundUser = null;

        try {
            String get = "select * from user where username = ?";

            statement = connection.prepareStatement(get);
            statement.setString(1, username);

            results = statement.executeQuery();

            if(!results.next()) {   //User not in database
                statement.close();
                //connection.rollback();
                return null;
            }

            String user = results.getString(1);
            String password = results.getString(2);

            foundUser = new User(user, password);

            statement.close();

            return foundUser;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String generateAuthToken(String username) {
        AuthToken newAuthToken = new AuthToken(username);
        String authTokenString = newAuthToken.getAuthToken();

        String update = "update user set auth_token = ? where username = ?";

        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, authTokenString);
            statement.setString(2, username);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return "";
        }

        return authTokenString;
    }

    @Override
    public boolean checkAuthToken(String username, String authToken) {
        String query = "select * from user where username = ? and auth_token = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, authToken);

            results = statement.executeQuery();

            if (!results.next()) {
                statement.close();
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void clearDatabase() {
        String drop = "drop table if exists person";

        runUpdate(drop);

        String create = "create table if not exists user (username text not null primary key, " +
                "password text not null, auth_token text);";

        runUpdate(create);
    }

    public void runUpdate(String update) {
        try {
            statement = connection.prepareStatement(update);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
