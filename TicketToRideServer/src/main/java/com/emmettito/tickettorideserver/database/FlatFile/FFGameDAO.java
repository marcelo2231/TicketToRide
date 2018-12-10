package com.emmettito.tickettorideserver.database.FlatFile;

import com.emmettito.models.Game;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.IGameDAO;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class FFGameDAO implements IGameDAO {
    Serializer serializer = new Serializer();

    String directory = System.getProperty("user.dir") + "/Games/";

    FFGameDAO() {
        new File(directory).mkdir();
    }

    @Override
    public boolean addGame(Game game, boolean isActive) {
        String gameDirect = "";

        if (isActive) {
            gameDirect = directory + "Active/";
        }
        else {
            gameDirect = directory + "Lobby/";
        }

        new File(gameDirect).mkdir();

        String fileName = gameDirect + game.getGameName() + ".txt";
        try {
            String gameJson = serializer.serialize(game);
            PrintWriter out = new PrintWriter(fileName);
            out.println(gameJson);
            out.close();
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean removeGame(String gameName) {
        String direct = directory + "Lobby/";

        File file = searchFiles(gameName, direct);

        if (file != null) {
            file.delete();
            return true;
        }

        direct = directory + "Active/";

        file = searchFiles(gameName, direct);

        if (file != null) {
            file.delete();
            return true;
        }

        return false;
    }

    public File searchFiles(String gameName, String directory) {
        File[] files = new File(directory).listFiles();
        if (files == null) {
            return null;
        }
        for (File f : files){
            try{
                String content = new Scanner(f).useDelimiter("\\Z").next();

                Game game = (Game)serializer.deserialize(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)), Game.class);
                if(game.getGameName().equals(gameName)){
                    //f.delete();
                    return f;
                }
            }catch (Exception e){
            }
        }

        return null;
    }

    @Override
    public Game updateGame(String gameName, String updatedGame) {
        File gameFile = searchFiles(gameName,directory + "/Active");

        if (gameFile == null) {
            return null;
        }

        ByteArrayInputStream stream = new ByteArrayInputStream(updatedGame.getBytes(StandardCharsets.UTF_8));

        Game game;

        try {
            game = (Game) new Serializer().deserialize(stream, Game.class);
        } catch (Exception e) {
            return null;
        }

        gameFile.delete();

        addGame(game, true);

        return null;
    }

    @Override
    public Game getGame(String gameName) {
        String direct = directory + "Lobby/";

        File file = searchFiles(gameName, direct);

        if (file != null) {
            try{
                String content = new Scanner(file).useDelimiter("\\Z").next();

                Game game = (Game)serializer.deserialize(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)), Game.class);

                return game;
            }catch (Exception e){
            }
        }

        direct = directory + "Active/";

        file = searchFiles(gameName, direct);

        if (file != null) {
            try{
                String content = new Scanner(file).useDelimiter("\\Z").next();

                Game game = (Game)serializer.deserialize(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)), Game.class);

                return game;
            }catch (Exception e){
            }
        }

        return null;
    }

    @Override
    public ArrayList<Game> getGames(boolean onlyActiveGames) {
        File[] files = new File(directory + "Lobby/").listFiles();
        File[] filesActive = new File(directory + "Active/").listFiles();

        ArrayList<Game> games = new ArrayList<>();

        if (!onlyActiveGames) {
            for (int i = 0; i < files.length; i++) {
                String gameName = files[i].getName();
                gameName = gameName.replace(".txt", "");
                Game game = getGame(gameName);

                games.add(game);
            }
        }

        for (int i = 0; i < filesActive.length; i++) {
            String gameName = filesActive[i].getName();
            gameName = gameName.replace(".txt", "");

            Game game = getGame(gameName);

            games.add(game);
        }

        return games;
    }

    @Override
    public boolean clearDatabase() {
        File dir = new File(directory + "Lobby/");
        File dir2 = new File(directory + "Active/");
        try {
            FileUtils.deleteDirectory(dir);
            FileUtils.deleteDirectory(dir2);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
