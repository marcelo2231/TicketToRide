package com.emmettito.tickettorideserver.database.SQL;

import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.Game;
import com.emmettito.tickettorideserver.database.IGameDAO;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLGameDAO implements IGameDAO {

    private Connection conn;

    public SQLGameDAO() {
        this.conn = null;
    };


    @Override
    public boolean addGame(Game game) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO `Games` VALUES (?,?);";
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
        String sql = "SELECT * FROM `Games` WHERE GameID = ?;;";
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
        String sql = "DELETE from `Games` WHERE gameID = ?;";
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
    public Game updateGame(String gameName, List<Command> commands) {
        PreparedStatement stmt = null;
        String sql = "UPDATE `Games` SET Data = ? WHERE gameID = ?";
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
    public boolean addCommand(Command command) {
        return false;
    }

    @Override
    public List<Command> getCommands() {
        return null;
    }

    @Override
    public boolean clearCommands() {
        return false;
    }

    @Override
    public boolean clearGames() {
        PreparedStatement stmt = null;
        String sql = "Delete from `Games`";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.execute();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }

    @Override
    public void beginTransation() {

    }

    @Override
    public void endTransaction() {

    }
}
