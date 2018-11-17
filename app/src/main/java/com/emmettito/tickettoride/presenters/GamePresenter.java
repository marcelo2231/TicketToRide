package com.emmettito.tickettoride.presenters;

import android.widget.Button;
import android.widget.Toast;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.models.City;
import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.CommandModels.GameCommands.DiscardCardRequest;
import com.emmettito.models.CommandModels.GameCommands.DrawDestCardRequest;
import com.emmettito.models.CommandModels.GameCommands.GetCommandsRequest;
import com.emmettito.models.CommandModels.GameCommands.GetGameRequest;
import com.emmettito.models.CommandModels.GameCommands.PlayerTurnRequest;
import com.emmettito.models.CommandModels.GameCommands.SetGameRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.DrawDestCardResult;
import com.emmettito.models.Results.GetCommandsResult;
import com.emmettito.models.Results.GetGameResult;
import com.emmettito.models.Results.GetPlayersResult;
import com.emmettito.models.Results.Result;
import com.emmettito.models.Route;
import com.emmettito.models.Tuple;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.communication.Poller;
import com.emmettito.tickettoride.facades.ServerFacade;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;
import com.emmettito.tickettoride.views.GameActivity.Turns.NotMyTurn;
import com.emmettito.tickettoride.views.GameActivity.Turns.Turn;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GamePresenter implements Observer {

    private Client data = Client.getInstance();
    private ServerFacade facade = null;
    private GameActivity gameActivity;
    private Poller poller;
    private Turn turnState;

    public GamePresenter(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
        this.turnState = new NotMyTurn(); // TODO: Nobody should be able to play until everyone has selected their initial destination cards
//        this.turnState = new MyTurnNoAction(); // USED FOR DEBUG
    }

    @Override
    public void update(Observable observable, Object o) {
        Gson gson = new Gson();
        String resultString = (String) o;
        GetGameResult result = gson.fromJson(resultString, GetGameResult.class);

        Game newGame = result.getData();

        //currentGame.setTrainCardDeck(newGame.getTrainCardDeck());
        //currentGame.setDestinationCardDeck(newGame.getDestinationCardDeck());

        Player currentPlayer = data.getGame().getOnePlayer(data.getUser());

        //currentGame.setPlayers(newGame.getPlayers());
        //currentGame.setOnePlayer(currentPlayer);

        data.setGame(newGame);
        data.getGame().setOnePlayer(currentPlayer);

    }

    public void startPoller() {
        //client.getGame().addObserver(this);

        //client.addObserver(this);
        GetGameRequest request = new GetGameRequest();
        request.setGameName(data.getGameName());

        String requestString = new Gson().toJson(request);
        poller = new Poller("http://"+data.getIpAddress() +":8080/game/getgame", requestString);

        poller.addObserver(this);

        poller.start(1);
    }

    public ArrayList<Player> getPlayers(){
        facade = ServerFacade.getInstance(data.getIpAddress(), "8080");
        GetPlayersRequest request = new GetPlayersRequest(data.getGameName());
        GetPlayersResult result = facade.getPlayers(request);
        return result.getData();
    }

    public Game getGame(){
        facade = ServerFacade.getInstance(data.getIpAddress(), "8080");
        GetGameRequest request = new GetGameRequest();
        request.setGameName(data.getGameName());
        GetGameResult result = facade.getGame(request);
        return result.getData();
    }

    public DestinationCard drawDestCard(String playerName){
        facade = ServerFacade.getInstance(data.getIpAddress(), "8080");
        DrawDestCardRequest request = new DrawDestCardRequest();
        request.setGameName(data.getGameName());
        request.setPlayerName(playerName);
        DrawDestCardResult result = facade.drawDestCard(request);
        return result.getData();
    }

    public boolean discardDestCard(String playerName, int cardID){
        facade = ServerFacade.getInstance(data.getIpAddress(), "8080");
        DiscardCardRequest request = new DiscardCardRequest();
        request.setGameName(data.getGameName());
        request.setPlayerName(playerName);
        request.setCardID(cardID);
        Result result = facade.discardDestCard(request);
        return result.getSuccess();
    }

    public ArrayList<Command> getCommands(int atIndex){
        facade = ServerFacade.getInstance(data.getIpAddress(), "8080");
        GetCommandsRequest request = new GetCommandsRequest(data.getGameName(), atIndex);
        GetCommandsResult result = facade.getCommands(request);
        return result.getData();
    }

    public int endPlayerTurn(Game game){
        facade = ServerFacade.getInstance(data.getIpAddress(), "8080");
        PlayerTurnRequest request = new PlayerTurnRequest();
        //gets the name of the player whose turn it is
        request.setPlayerName(game.getPlayers().get(game.getPlayerTurnIndex()).getPlayerName());
        request.setGameName(game.getGameName());
        request.setGameIndex(game.getPlayerTurnIndex());
        Result result = facade.endTurn(request, data.getIpAddress(), "8080");
        int newIndex = -1;
        try{
            newIndex = (int) result.getData();
        }catch (Exception e){
            //testing that it works right
            if(game.getPlayerTurnIndex()+1 >= game.getPlayers().size()){
                return 0;
            }
            return game.getPlayerTurnIndex()+1;

            //if something went wrong, return the original index
            //return game.getPlayerTurnIndex();
        }
        return newIndex;
    }

//    public void drawFaceUpTrainCard(GameActivity gameActivity, Game game, TrainCard oldCard, TrainCard newCard, int trainCardIndex, Button trainButton){
////        game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards().add(oldCard);
////        game.getPlayers().get(game.getPlayerTurnIndex()).setTrainCards(game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards());
//        //gameActivity.addTrainCardToPlayer(oldCard);
//
//        //facade = ServerFacade.getInstance(data.getIpAddress(), "8080");
//        //DrawFaceUpTrainRequest request = new DrawFaceUpTrainRequest();
//        //request.setGameName(data.getGameName());
//        //request.setCardIndex(trainCardIndex);
//        //request.setPlayerName(data.getUser());
//        //DrawTrainResult result = facade.drawFaceUpTrainCard(request);
//        //gameActivity.addTrainCardToPlayer(result.getData());
//        //return result.getData();
//
//        //TrainCard currentTrainCard = getGame().getTrainCardDeck().getFaceUpCards().get(trainCardIndex);
//
//        //if (currentTrainCard != null) {
//            trainButton.setBackground(gameActivity.updateFaceUpCard(currentTrainCard));
//        }
//
//        if(gameActivity.checkTrainCardDeck()){
//            data.getGame().getTrainCardDeck().getFaceUpCards().set(trainCardIndex, result.getData());
//            trainButton.setBackground(gameActivity.updateFaceUpCard(result.getData()));
//            gameActivity.updateFaceUpCards();
//        }
//        else{
//            trainButton.setBackgroundColor(0x00);
//            trainButton.setBackgroundResource(android.R.drawable.btn_default);
//            //game.getTrainCardDeck().getFaceUpCards().add(trainCardIndex, null);
//            trainButton.setEnabled(false);
//        }
//    }

    //public void drawTrainCard(GameActivity gameActivity, TrainCard oldCard){
//        game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards().add(oldCard);
//        game.getPlayers().get(game.getPlayerTurnIndex()).setTrainCards(game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards());
        //gameActivity.addTrainCardToPlayer(oldCard);

        //facade = ServerFacade.getInstance(data.getIpAddress(), "8080");
        //DrawTrainRequest request = new DrawTrainRequest();
        //request.setGameName(data.getGameName());
        //request.setPlayerName(data.getUser());
        //DrawTrainResult result = facade.drawTrainCard(request);
        //return result.getData();

        //if(result.getSuccess()){}
        //else{
        //    gameActivity.notifyDeckEmpty();
        //}
    //}

    public void endTurn() {
        data.getGame().setPlayerTurnIndex(endPlayerTurn(data.getGame()));
        startPoller();
    }





    /*

    Turn state functions

     */

    public Turn getTurnState() {
        return this.turnState;
    }

    public void setTurnState(Turn turnState) {
        this.turnState = turnState;
    }

    public void displayToast(String msg) {
        Toast.makeText(gameActivity.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public GameActivity getGameActivity() {
        return gameActivity;
    }

    public void enterChat() {
        turnState.enterChat(this);
    }

    public void leaveGame() {
        turnState.leaveGame(this);
    }

    public void viewCommands() {
        turnState.viewCommands(this);
    }

    public void viewDestCard() {
        turnState.viewDestCard(this);
    }

    public void drawFaceUpTrainCard(Button trainCardButton, int buttonIndex) {
        turnState.drawFaceUpTrainCard(this, trainCardButton, buttonIndex);
    }

    public void drawFaceDownTrainCard() {
        turnState.drawFaceDownTrainCard(this);
    }

    public void drawDestCards() {
        turnState.drawDestCards(this);
    }

    public void claimRoute(int routeID) {
        turnState.claimRoute(this, routeID);
    }

    /*

    Claiming a route

    */

    // SHOULD ONLY BE CALLED BY THE TURN STATES
    private int numOfColor(TrainColor color, List<TrainCard> cards) {
        int num = 0;

        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getColor() == color) {
                num++;
            }
        }

        return num;
    }

    // SHOULD ONLY BE CALLED BY THE TURN STATES
    private void removeTrainCardsFromPlayer(int count, TrainColor color) {
        Player player = data.getGame().getOnePlayer(data.getUser());
        List<TrainCard> cards = player.getTrainCards();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < cards.size(); j++) {
                if (cards.get(j).getColor() == color) {
                    cards.remove(j);
                    break;
                }
            }
        }
    }

    // SHOULD ONLY BE CALLED BY THE TURN STATES
    private boolean isRouteAvailable(int routeID) {
        if (isRouteTaken(routeID)) {
            return false;
        }

        Route route = data.getAllRoutes().get(routeID);
        int double_route = route.getDoubleRoute();

        if (double_route != -1) { // if the route has a double route
            int num_players = data.getGame().getPlayers().size();
            final int MIN_NUM_FOR_DOUBLE = 3;

            if (num_players < MIN_NUM_FOR_DOUBLE) {
                return !isRouteTaken(double_route);
            }
            else {
                Player player = data.getGame().getOnePlayer(data.getUser());
                return !player.getClaimedRoutes().contains(double_route);
            }
        }


        return true;
    }

    // SHOULD ONLY BE CALLED BY THE TURN STATES
    private boolean isRouteTaken(int routeID) {
        List<Player> players = data.getGame().getPlayers();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            List<Integer> routes = player.getClaimedRoutes();
            if (routes.contains(routeID)) {
                return true;
            }
        }

        return false;
    }

    // SHOULD ONLY BE CALLED BY THE TURN STATES
    public boolean canClaimRoute(int routeID, TrainColor chosen_color) {
        if (!isRouteAvailable(routeID)) {
            Tuple cities = data.getAllRoutes().get(routeID).getCities();
            City city_x = data.getAllCities().get((int)cities.getX());
            City city_y = data.getAllCities().get((int)cities.getY());
            String error = "You cannot claim the route from " + city_x.getName() + " to " + city_y.getName();
            displayToast(error);
            return false;
        }

        Route route = data.getAllRoutes().get(routeID);

        TrainColor route_color = route.getTrainColor();

        if (route_color != null) { //
            if (route_color != chosen_color && chosen_color != TrainColor.Wild) {
                String error = "The chosen color does not match the color required for this route (" + routeID + "): " + route_color.toString();
                displayToast(error);
                return false;
            }
        }

        int route_size = route.getSpaces().size();

        Player player = data.getGame().getOnePlayer(data.getUser());

        if (player.getPlasticTrains() < route_size) {
            String error = "You do not have enough trains to claim this route.";
            displayToast(error);
            return false;
        }

        List<TrainCard> cards = player.getTrainCards();

        int num_color = numOfColor(chosen_color, cards);
        int num_wilds = numOfColor(TrainColor.Wild, cards);

        if (num_color >= route_size) {
            return true;
        }
        else if (num_color + num_wilds >= route_size) {
            return true;
        }
        else {
            String error = "You do not have sufficient train cards to claim this route.";
            displayToast(error);
            return false;
        }
    }

    /**
     * @pre assumes that the route is claimable and the player has sufficient train cards;
     * should only be called after canClaimRoute returns true.
     *
     * SHOULD ONLY BE CALLED BY THE TURN STATES
     */
    public void claimRoute(int routeID, TrainColor chosen_color) {
        Route route = data.getAllRoutes().get(routeID);

        int route_size = route.getSpaces().size();

        Player player = data.getGame().getOnePlayer(data.getUser());
        List<TrainCard> cards = player.getTrainCards();

        int num_color = numOfColor(chosen_color, cards);

        if (num_color >= route_size) {
            removeTrainCardsFromPlayer(route_size, chosen_color);
        }
        else {
            removeTrainCardsFromPlayer(num_color, chosen_color);
            removeTrainCardsFromPlayer(route_size - num_color, TrainColor.Wild);
        }

        player.getClaimedRoutes().add(routeID);
        player.setPoints(player.getPoints() + route.getPointValue());
        player.setPlasticTrains(player.getPlasticTrains() - route.getSpaces().size());

        // TODO: needs to send updates to the server
        setGame(data.getGame());

        gameActivity.updatePlayerDisplay();
    }

    public void drawDestCard(boolean isFirstTime) {
        gameActivity.drawDestCard(isFirstTime);
    }

    public Result setGame(Game game) {
        facade = ServerFacade.getInstance(data.getIpAddress(), "8080");
        SetGameRequest request = new SetGameRequest();
        request.setGame(game);
        return facade.setGame(request);
    }

}
