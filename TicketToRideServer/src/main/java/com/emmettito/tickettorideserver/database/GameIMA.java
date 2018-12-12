package com.emmettito.tickettorideserver.database;

import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Tuple;
import com.emmettito.tickettorideserver.game.IGameCommand;
import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

//import static com.sun.xml.internal.org.jvnet.fastinfoset.FastInfosetSerializer.UTF_8;

public class GameIMA {
    /** Variables **/
    private static InternalMemory dbInstance = InternalMemory.getInstance();




    /* Start of methods moved from the GameLobbyDao */

    public void addGame(Game newGame, boolean isActive) throws DuplicateName {
        IGameDAO dao = dbInstance.getGameDAO();

        if (getGame(newGame.getGameName()) != null) {
            throw new DuplicateName();
        }

        dao.addGame(newGame, isActive);
    }

    public Game getGame(String gameName) {
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

    public Game setGame(Game game) {
        IGameDAO dao = dbInstance.getGameDAO();

        String updatedGame = new Gson().toJson(game);

        return dao.updateGame(game.getGameName(), updatedGame);
    }

    public ArrayList<Game> getGames() {
        IGameDAO dao = dbInstance.getGameDAO();

        return dao.getGames(false); //Return all games
    }

    public ArrayList<Game> getActiveGames(){
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

    public void addDeltaCommand(String gameName, String authToken, Tuple commandTuple) {
        Map<String, ArrayList<Tuple>> commands = dbInstance.deltaCommands;

        ArrayList<Tuple> list = new ArrayList<>();

        IGameCommand command = (IGameCommand) commandTuple.getX();
        Object information = commandTuple.getY();

        String requestString = null;
        try {
            requestString = IOUtils.toString((InputStream) information);
        } catch (Exception e) {
            e.printStackTrace();
        }

        information = IOUtils.toInputStream(requestString);

        try {
            command.execute(information, authToken);
        } catch (Exception e) {
            e.printStackTrace();
        }

        commandTuple.setY(IOUtils.toInputStream(requestString));

        if (commands.containsKey(gameName)) {
            list = commands.get(gameName);
            list.add(commandTuple);
        }
        else {
            list.add(commandTuple);
        }

        Game game = getGame(gameName);

        InputStream newStream = null;

        Game newGame = getGame(gameName);

        game.setCommands(newGame.getCommands());

        setGame(game);

        commands.put(gameName, list);

        if (list.size() >= dbInstance.getDeltaNum()) {
            checkpoint(list, gameName);

            list.clear();
            list = new ArrayList<>();

            commands.put(gameName, list);
        }
    }

    public void checkpoint(ArrayList<Tuple> list, String gameName) {
        for (int i = 0; i < list.size(); i++ ){
            Tuple tuple = list.get(i);
            ArrayList<Command> commands = getGame(gameName).getCommands();

            IGameCommand command = (IGameCommand) tuple.getX();
            Object information = tuple.getY();

            try {
                command.execute(information, "");
            } catch (Exception e) {
                e.printStackTrace();
            }

            Game game = getGame(gameName);
            game.setCommands(commands);
            setGame(game);
        }
    }
}
