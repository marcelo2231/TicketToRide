package com.emmettito.tickettorideserver.game;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.CommandModels.GameLobbyCommands.StartGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.PlayerColor;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.DeckIMA;

import java.io.InputStream;
import java.util.ArrayList;

public class StartGameCommand implements IGameCommand {
    StartGameRequest commandModel;
    DeckIMA deckDao = new DeckIMA();

    @Override
    public Object execute(Object obj, String authToken) throws Exception {
        /** Validate **/
        try {
            commandModel = (StartGameRequest)new Serializer().deserialize((InputStream)obj, StartGameRequest.class);
        }catch(Exception e){
            throw new Exception("StartGameCommand: command was null, please, make sure to set the StartGameCommandModel.");
        }
        if(!userIMA.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        /** Get Game **/
        Game currGame = gameIMA.getGame(commandModel.getGameName());
        if (currGame == null) {
            throw new Exception("Unable to find game.");
        }

        if (currGame.getPlayers().size() < 2){
            throw new Exception("Unable to start game. You must have at least 2 players.");
        }

        ArrayList<Player> players = currGame.getPlayers();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            player.setPosition(i + 1);
            player.setColor(PlayerColor.values()[player.getPosition() - 1]);
            players.set(i, player);
        }

        currGame.setPlayers(players);

        /** Move to active game **/
        gameIMA.removeGame(currGame.getGameName());
        currGame.setStarted(true);
        gameIMA.addGame(currGame, true);

        /** Add 4 Train Cards per player **/
        players = currGame.getPlayers();
        for(Player p : players){
            for(int i = 0; i < 4; i++){
                TrainCard card = deckDao.removeTopTrainCardFromDeck(commandModel.getGameName());
                deckDao.addTrainCardToPlayer(commandModel.getGameName(), p.getPlayerName(), card);
            }
        }

//        /** Add 3 Destination Cards per player **/
//        for(Player p : players){
//            for(int i = 0; i < 4; i++){
//                DestinationCard card = deckDao.removeTopDestCardFromDeck(commandModel.getGameName());
//                deckDao.addDestCardToPlayer(commandModel.getGameName(), p.getPlayerName(), card);
//            }
//        }

        /** Prepare Result **/
        GameLobbyResult gameLobbyResult = new GameLobbyResult();
        gameLobbyResult.setSuccess(true);
        gameLobbyResult.setMessage("Game started successfully.");
        return gameLobbyResult;
    }
}
