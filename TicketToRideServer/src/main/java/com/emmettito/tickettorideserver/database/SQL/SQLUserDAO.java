package com.emmettito.tickettorideserver.database.SQL;

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

    SQLUserDAO() {
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
        return false;
    }

    public User getUser(String username) {
        User returnValue = null;

        String get = "select * from user where username = ?";

        try {
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(get);
            statement.setString(1, username);
            statement.executeUpdate();

            results = statement.executeQuery();

            if(!results.next()) {   //User not in database
                statement.close();
                connection.rollback();
                try {
                    connection.commit();
                } catch (SQLException e) {
                    if (connection != null) {
                        System.err.print("Connection is being rolled back");
                        connection.rollback();
                    }
                } finally {
                    connection.setAutoCommit(true);

                    if (statement != null) {
                        statement.close();
                    }
                }
                return null;
            }

            String user = results.getString(1);
            String password = results.getString(2);

            returnValue = new User(user, password);

            statement.close();
            try {
                connection.commit();
            } catch (SQLException e) {
                if (connection != null) {
                    System.err.print("Connection is being rolled back");
                    connection.rollback();
                }
            } finally {
                connection.setAutoCommit(true);

                if (statement != null) {
                    statement.close();
                }
            }

            return returnValue;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException f) {
                f.printStackTrace();
            }
        }

        return returnValue;
    }

    @Override
    public String generateAuthToken(String username) {
        return null;
    }

    @Override
    public boolean checkAuthToken(String authToken) {
        return false;
    }

    @Override
    public void clearDatabase() {

    }
}
