package com.emmettito.tickettorideserver.database.FlatFile;

import com.emmettito.models.Game;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.IGameDAO;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintWriter;

public class FFGameDAO implements IGameDAO {
    Serializer serializer = new Serializer();
    @Override
    public boolean addGame(Game game) {
        String fileName = "Games/" + game.getGameName();
        try {
            String gameJson = serializer.serialize(game);
            PrintWriter out = new PrintWriter(fileName);
            out.println(gameJson);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean removeGame(String gameName) {
        File[] files = new File("Games/").listFiles();
        for (File f : files){
            try{
                Game game = (Game)serializer.deserialize(new ByteArrayInputStream(f.toString().getBytes()), Game.class);
                if(game.getGameName().equals(gameName)){
                    f.delete();
                    return true;
                }
            }catch (Exception e){
            }
        }
        return false;
    }

    @Override
    public Game updateGame(String gameName, String updatedGame) {
        return null;
    }

//    @Override
//    public Game updateGame(String gameName, List<Command> commands) {
//        File[] files = new File("Games/").listFiles();
//        for (File f : files){
//            try{
//                Game game = (Game)serializer.deserialize(new ByteArrayInputStream(f.toString().getBytes()), Game.class);
//                if(game.getGameName().equals(gameName)){
//                    game.setCommands(new ArrayList<>(commands));
//                    removeGame(gameName);
//                    addGame(game);
//                    return game;
//                }
//            }catch (Exception e){
//            }
//        }
//        return null;
//    }

    @Override
    public boolean addCompletedGame(String gameName) {
        return false;
        // DO NOT IMPLEMENT... TO BE REMOVED
    }

    @Override
    public Game getGame(String gameName) {
        File[] files = new File("Games/").listFiles();
        for (File f : files){
            try{
                Game game = (Game)serializer.deserialize(new ByteArrayInputStream(f.toString().getBytes()), Game.class);
                if(game.getGameName().equals(gameName)){
                    return game;
                }
            }catch (Exception e){
            }
        }
        return null;
    }

    @Override
    public boolean clearDatabase() {
        return false;
    }

//    @Override
//    public boolean clearGames() {
//        File dir = new File("Games");
//        try {
//            FileUtils.deleteDirectory(dir);
//            return true;
//        }
//        catch (Exception e){
//            return false;
//        }
//    }
}
