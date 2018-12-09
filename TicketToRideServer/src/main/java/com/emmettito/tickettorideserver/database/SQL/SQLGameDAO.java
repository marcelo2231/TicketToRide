package com.emmettito.tickettorideserver.database.SQL;

import com.emmettito.models.Game;
import com.emmettito.tickettorideserver.database.IGameDAO;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLGameDAO implements IGameDAO {

    private Connection conn;

    public SQLGameDAO() {
        String driver = "org.sqlite.JDBC";

        try {
            Class.forName(driver);

            String dbname = "jdbc:sqlite:games.db";     //Name of the database file

            conn = DriverManager.getConnection(dbname);

            String create = "create table if not exists games (gamename text not null primary key, " +
                    "game blob);";

            PreparedStatement stmt = conn.prepareStatement(create);
            stmt.executeUpdate();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    };


    @Override
    public boolean addGame(Game game) {
        PreparedStatement stmt = null;
        String sql = "insert games values (?,?)";
        Gson gson = new Gson();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, game.getGameName());
            stmt.setString(2, gson.toJson(game));
            stmt.execute();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }

        return true;
    }

    @Override
    public boolean addCompletedGame(String gameName) {
        return false;
    }

    @Override
    public Game getGame(String gameName) {
        PreparedStatement stmt = null;
        String sql = "select * from games where gamename = ?";
        ResultSet rs = null;
        Gson gson = new Gson();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, gameName);
            rs = stmt.executeQuery();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        Game returnedGame = gson.fromJson(rs.toString(), Game.class);
        return returnedGame;
    }

    @Override
    public boolean removeGame(String gameName) {
        PreparedStatement stmt = null;
        String sql = "delete from games where gamename = ?";
        Gson gson = new Gson();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, gameName);
            stmt.execute();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }

        return true;
    }

    @Override
    public Game updateGame(String gameName, String updatedGame) {
        PreparedStatement stmt = null;
        String sql = "update games set game = ? where gamename = ?";
        Gson gson = new Gson();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, gameName);
            stmt.execute();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }

        return null;
    }

    @Override
    public boolean clearDatabase() {
        PreparedStatement stmt = null;
        String sql = "delete from games";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.execute();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }
}
