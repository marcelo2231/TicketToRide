package com.emmettito.tickettorideserver.gameLobby;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.DestinationCardDeck;
import com.emmettito.models.Cards.TrainCardDeck;
import com.emmettito.models.CommandModels.GameLobbyCommands.StartGameRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettorideserver.communication.Serializer;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.awt.Color;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class StartGameCommand implements IGameLobbyCommand {
    StartGameRequest commandModel;

    @Override
    public Object execute(Object obj, String authToken) throws Exception {
        /** Validate **/
        try {
            commandModel = (StartGameRequest)new Serializer().deserialize((InputStream)obj, StartGameRequest.class);
        }catch(Exception e){
            throw new Exception("StartGameCommand: command was null, please, make sure to set the StartGameCommandModel.");
        }
        if(!userDatabase.authTokenAndUserAreValid(authToken, commandModel.getPlayerName())){
            throw new Exception("Invalid authToken or playerName not authorized to user this token. You do not have authorization to execute this command.");
        }

        /** Get Game **/
        Game currGame = gameLobbyDatabase.getGame(commandModel.getGameName());
        if (currGame == null) {
            throw new Exception("Unable to find game.");
        }

        if (currGame.getPlayers().size() < 2){
            throw new Exception("Unable to start game. You must have at least 2 players.");
        }

        /** Move to active game **/
        gameLobbyDatabase.removeGame(currGame.getGameName());
        gameLobbyDatabase.addActiveGame(currGame);

        /** Add 4 Train Cards per player **/
        ArrayList<Player> players = currGame.getPlayers();
        TrainCardDeck trainCardDeck = currGame.getTrainCardDeck();
        for(Player p : players){
            for(int i = 0; i < 4; i++){
                p.getTrainCards().add(trainCardDeck.drawCard());
            }
        }

        /** Add 3 Destination Cards per player **/
        DestinationCardDeck destinationCardDeck = currGame.getDestinationCardDeck();
        for(Player p : players){
            for(int i = 0; i < 4; i++){
                p.getDestinationCards().add(destinationCardDeck.drawCard());
            }
        }

        /** Prepare Result **/
        GameLobbyResult gameLobbyResult = new GameLobbyResult();
        gameLobbyResult.setSuccess(true);
        gameLobbyResult.setMessage("Game started successfully.");
        return gameLobbyResult;
    }
}
