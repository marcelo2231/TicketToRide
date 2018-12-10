package com.emmettito.tickettorideserver.database.SQL;

import com.emmettito.models.Game;
import com.emmettito.tickettorideserver.database.IGameDAO;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLGameDAO implements IGameDAO {

    private Connection conn;

    public SQLGameDAO() {
        String driver = "org.sqlite.JDBC";

        String sqlite_directory = System.getProperty("user.dir") + "/plugins/sqlite/";

        try {
            Class.forName(driver);

            String dbname = "jdbc:sqlite:" + sqlite_directory + "games.db";     //Name of the database file

            conn = DriverManager.getConnection(dbname);

            String create = "create table if not exists games (gamename text not null primary key, " +
                    "game blob, is_active text not null);";

            PreparedStatement stmt = conn.prepareStatement(create);
            stmt.executeUpdate();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    };


    @Override
    public boolean addGame(Game game, boolean isActive) {
        PreparedStatement stmt = null;
        String sql = "insert into games values (?,?,?)";
        Gson gson = new Gson();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, game.getGameName());
            stmt.setString(2, gson.toJson(game));

            if (isActive) {
                stmt.setString(3, "yes");
            }
            else {
                stmt.setString(3, "no");
            }

            stmt.execute();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }

        return true;
    }

    @Override
    public Game getGame(String gameName) {
        PreparedStatement stmt = null;
        String sql = "select game from games where gamename = ?";
        ResultSet rs = null;
        Gson gson = new Gson();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, gameName);
            rs = stmt.executeQuery();

            if(!rs.next()) {   //User not in database
                stmt.close();
                //connection.rollback();
                return null;
            }

            String serializedGame = rs.getString(1);

            return gson.fromJson(serializedGame, Game.class);
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }

        return null;
    }

    @Override
    public ArrayList<Game> getGames(boolean onlyActiveGames) {
        PreparedStatement stmt = null;
        String sql = "";

        if (onlyActiveGames) {
            sql = "select game from games where is_active = ?";
        }
        else {
            sql = "select game from games";
        }

        ResultSet rs = null;
        Gson gson = new Gson();
        try {
            stmt = conn.prepareStatement(sql);
            if (onlyActiveGames) {
                stmt.setString(1, "yes");
            }
            rs = stmt.executeQuery();

            ArrayList<Game> games = null;

            if (rs.next()) {
                games = new ArrayList<>();

                do {
                    String gameString = rs.getString(1);

                    Game game = gson.fromJson(gameString, Game.class);

                    games.add(game);
                } while (rs.next());
            }

            stmt.close();

            return games;
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    @Override
    public boolean removeGame(String gameName) {
        PreparedStatement stmt = null;
        String sql = "delete from games where gamename = ?";
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
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, updatedGame);
            stmt.setString(2, gameName);
            stmt.execute();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }

        return null;
    }

    @Override
    public boolean clearDatabase() {
        String drop = "drop table if exists games";

        runUpdate(drop);

        String create = "create table if not exists games (gamename text not null primary key, " +
                "game blob, is_active text not null);";

        runUpdate(create);

        return true;
    }

    public void runUpdate(String update) {
        try {
            PreparedStatement statement = conn.prepareStatement(update);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
