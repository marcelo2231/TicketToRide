package com.emmettito.tickettorideserver.database;

import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.google.gson.Gson;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.util.ArrayList;

public class GameIMA {
    /** Variables **/
    private static InternalMemory dbInstance = InternalMemory.getInstance();




    /* Start of methods moved from the GameLobbyDao */

    public void addGame(Game newGame, boolean isActive) throws DuplicateName {
        /*if (getGame(newGame.getGameName()) != null) {
            throw new DuplicateName();
        }
        else {
            dbInstance.gameLobby.add(newGame);
        }*/

        IGameDAO dao = dbInstance.getGameDAO();

        if (getGame(newGame.getGameName()) != null) {
            throw new DuplicateName();
        }

        dao.addGame(newGame, isActive);
    }

    public Game getGame(String gameName) {
        /*for (Game g : dbInstance.gameLobby) {
            if (g.getGameName().equals(gameName)) {
                return g;
            }
        }
        for (Game g : dbInstance.activeGame) {
            if (g.getGameName().equals(gameName)) {
                return g;
            }
        }
        for (Game g : dbInstance.endedGame) {
            if (g.getGameName().equals(gameName)) {
                return g;
            }
        }*/

        IGameDAO dao = dbInstance.getGameDAO();

        Game game = dao.getGame(gameName);

        if (game == null) {
            for (Game g : dbInstance.endedGame) {
                if (g.getGameName().equals(gameName)) {
                    return g;
                }
            }
        }

        return game;
    }

    /*public Game getActiveGame(String gameName) {
        for (Game g : dbInstance.activeGame) {
            if (g.getGameName().equals(gameName)) {
                return g;
            }
        }
        return null;
    }*/

    public Game setGame(Game game) {
        /*for (Game g : dbInstance.activeGame){
            if(g.getGameName().equals(game.getGameName())){
                dbInstance.activeGame.remove(g);
                dbInstance.activeGame.add(game);
            }
        }
        return null;*/

        IGameDAO dao = dbInstance.getGameDAO();

        String updatedGame = new Gson().toJson(game);

        return dao.updateGame(game.getGameName(), updatedGame);
    }

    public ArrayList<Game> getGames() {
        /*ArrayList<Game> games = new ArrayList<>();
        for(Game g : dbInstance.gameLobby){
            games.add(g);
        }
        for(Game g : dbInstance.activeGame){
            games.add(g);
        }
        return games;*/

        IGameDAO dao = dbInstance.getGameDAO();

        return dao.getGames(false); //Return all games
    }

    public ArrayList<Game> getActiveGames(){
        //return dbInstance.activeGame;

        IGameDAO dao = dbInstance.getGameDAO();

        return dao.getGames(true); //Return only active games
    }

    public void removeGame(String gameName) throws NotFound {
        Game toBeRemoved = getGame(gameName);
        if (toBeRemoved == null){
            throw new NotFound();
        }
        if (!dbInstance.getGameDAO().removeGame(gameName)) {
            throw new NotFound();
        }
    }

    /*public void removeActiveGame(String gameName) throws NotFound {
        Game toBeRemoved = getGame(gameName);
        if (toBeRemoved == null){ throw new NotFound(); }
        if (!dbInstance.activeGame.remove(toBeRemoved)) { throw new NotFound(); }
    }*/

    /*public void addActiveGame(Game newGame) { //two active games can have the same name
        dbInstance.activeGame.add(newGame);
    }*/

    public void addEndedGame(Game endedGame) {
        dbInstance.endedGame.add(endedGame);
    }

    /* End of methods moved from the GameLobbyDao */




    /** Player **/
    public void addPlayer(String gameName, Player newPlayer) throws Exception {
        // Get Game
        Game game = getGame(gameName);

        System.out.println("Here");

        // Game Validation
        if (game == null){
            throw new Exception("Game does not exist.");
        }

        // Get Players
        ArrayList<Player> newList = game.getPlayers();

        // Players Validation
        if (newList.size() >= 5) {
            throw new Exception("Error, game has max number of players.");
        }
        for (Player p : newList){
            if(p.getPlayerName().equals(newPlayer.getPlayerName())){
                throw new Exception("Player already in game.");
            }
        }

        System.out.println("There");

        // Add newPlayer
        newList.add(newPlayer);
        game.setPlayers(newList);
        Game games = setGame(game);

        System.out.println("Everywhere");
    }

    /*public void removePlayer(String gameName, Player targetPlayer) throws Exception{
        // Get Game
        Game game = getGame(gameName);

        // Game Validation
        if (game == null){
            throw new Exception("Game does not exist.");
        }

        // Get Players
        ArrayList<Player> newList = game.getPlayers();

        // Players Validation
        if (newList == null || !newList.remove(targetPlayer)) {
            throw new NotFound();
        }
    }*/

    public Player getPlayer(String gameName, String playerName) throws Exception{
        // Get Game
        Game game = getGame(gameName);

        // Game Validation
        if (game == null){
            throw new Exception("Game does not exist.");
        }

        // Find Player
        for(Player p : game.getPlayers()){
            if(p.getPlayerName().equals(playerName)){
                return p;
            }
        }
        return null;
    }

    public ArrayList<Player> getPlayers(String gameName) throws Exception{
        // Get Game
        Game game = getGame(gameName);

        // Game Validation
        if (game == null){
            throw new Exception("Game does not exist.");
        }

        // Return Player
        return game.getPlayers();
    }

    public int incrementTurn(String gameName) throws Exception{
        // Get Game
        Game game = getGame(gameName);

        // Game Validation
        if (game == null){
            throw new Exception("Game does not exist.");
        }

        // Increment turn
        game.incrementTurnIndex();
        setGame(game);
        return game.getPlayerTurnIndex();
    }

    public ArrayList<Command> getCommands(String gameName, int currentIndex) throws Exception{
        // Get Game
        Game game = getGame(gameName);

        if (game == null) { throw new Exception("Invalid game name."); }

        ArrayList<Command> commands = game.getCommands();

        if (commands == null) { throw new Exception("Commands list is null"); }

        if (currentIndex > commands.size()){
            throw new Exception("Invalid size");
        }
        if(currentIndex == commands.size()){
            throw new Exception("You are up to date");
        }

        return new ArrayList<>(commands.subList(currentIndex, commands.size()));
    }

    public void addCommand(String gameName, Command command){
        // Get Game
        Game game = getGame(gameName);
        game.getCommands().add(command);
        setGame(game);
    }

}
