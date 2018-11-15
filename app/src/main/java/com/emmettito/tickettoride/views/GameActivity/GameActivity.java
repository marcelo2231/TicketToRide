package com.emmettito.tickettoride.views.GameActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainCardDeck;
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.models.City;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Route;
import com.emmettito.models.Tuple;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.GamePresenter;
import com.emmettito.tickettoride.views.GameActivity.Turns.MyTurnNoAction;
import com.emmettito.tickettoride.views.GameActivity.Turns.NotMyTurn;
import com.emmettito.tickettoride.views.GameActivity.Turns.Turn;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends FragmentActivity implements DrawDestCardFragment.OnFragmentInteractionListener, DestCardDisplayFragment.OnFragmentInteractionListener {

    private GamePresenter presenter = new GamePresenter(this);

    private Client data;
    private Context context = this;

    private Button deckTrainCards;
    private Button deckDestinationCards;

    private List<String[]> players = new ArrayList<>();
    private RecyclerView.Adapter playerListAdapter;
    private RecyclerView.Adapter playerTrainCardsAdapter;

    private  MapView mapView = null;

    private Turn turnState;

    private GameActivity thisGameActivity;

    public void setTurnState(Turn turnState) {
        this.turnState = turnState;
    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            //mAdapter.notifyDataSetChanged();
            thisGameActivity.updatePlayerDisplay();
            thisGameActivity.updateDestinationCardDeck();
            thisGameActivity.updateCardDeck();
            thisGameActivity.updatePlayerTrainCards();
            thisGameActivity.updateMapView();
            thisGameActivity.checkIfOurTurn();
            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_game);

        thisGameActivity = this;

        data = Client.getInstance();

        Game game = presenter.getGame();
        data.setGame(game);

        // Get players
        final ArrayList<Player> playerList = presenter.getPlayers();

        System.out.println(playerList);

        game.setPlayers(playerList);
        setPlayerList(playerList);

        setMapViewOnCreateListener();
        setPlayerTrainCards();
        setGameButtons();
        setTrainCardDeck();
        setDestinationCardDeck();

        /** Set up recycler view **/

        RecyclerView playerListRecycle = findViewById(R.id.playerListView);
        playerListRecycle.setLayoutManager(new LinearLayoutManager(this));
        playerListAdapter = new PlayerInfoAdapter(players);
        playerListRecycle.setAdapter(playerListAdapter);


        // activate the draw card buttons if it's the player's turn
        Player player = game.getOnePlayer(data.getUser());

        if(game.isPlayerTurn(player)){
            turnState = new MyTurnNoAction();
//            deckTrainCards.setEnabled(true); // TODO: Buttons should always be enabled, but the actions should depend on the turn state functionality
//            deckDestinationCards.setEnabled(true);
        }
        else{
            turnState = new NotMyTurn();
//            deckTrainCards.setEnabled(false);
//            deckDestinationCards.setEnabled(false);
        }

        //presenter.addObserver();
        timerHandler.postDelayed(timerRunnable, 500);

        //after setting up/inflating, initialize the game-starting processes
        drawDestCard(true);
        presenter.startPoller();
        startGame();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    @Override
    public void onBackPressed() {}

    @Override
    public void onResume() {
        super.onResume();
        updatePlayerDisplay();
        updateDestinationCardDeck();
    }

    private void startGame(){
        updatePlayerDisplay();
        updateDestinationCardDeck();
    }

    /*

    Main Game Buttons

     */

    private void setGameButtons() {
        Button chatButton = findViewById(R.id.openChatButton);
        chatButton.setEnabled(true);
        chatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                turnState.enterChat((GameActivity)context);
            }
        });

        Button leaveGameButton = findViewById(R.id.leaveGameButton);
        leaveGameButton.setEnabled(true);
        leaveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnState.leaveGame((GameActivity)context);
            }
        });

        Button displayCommandsButton = findViewById(R.id.displayCommandsButton);
        displayCommandsButton.setEnabled(true);
        displayCommandsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnState.viewCommands((GameActivity)context);
            }
        });

        Button viewDestinationCardsButton = findViewById(R.id.viewDestinationCardsButton);
        viewDestinationCardsButton.setEnabled(true);
        viewDestinationCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnState.viewDestCard((GameActivity)context);
            }
        });
    }

    public void enterChat() {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        startActivity(intent);
    }

    public void leaveGame() {
        Toast.makeText(getApplicationContext(), "Leaving game", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void viewCommands() {
        Intent intent = new Intent(getApplicationContext(), GameHistoryActivity.class);
        startActivity(intent);
    }

    public void viewDestCard() {
        Fragment displayDestCardFragment = new DestCardDisplayFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, displayDestCardFragment);
        transaction.commit();

        updatePlayerDisplay();
        updateDestinationCardDeck();
    }

    /*

     Train Cards

     */

    public class TrainCardClickListener implements View.OnClickListener {
        private Button trainCardButton;
        private int buttonIndex;

        public TrainCardClickListener(Button trainCardButton, int buttonIndex) {
            this.trainCardButton = trainCardButton;
            this.buttonIndex = buttonIndex;
        }

        @Override
        public void onClick(View v) {
            turnState.drawFaceUpTrainCard((GameActivity) context, trainCardButton, buttonIndex);
        }
    }

    private void setTrainCardDeck() {
        TrainCardDeck deck = data.getGame().getTrainCardDeck();

        deckTrainCards = findViewById(R.id.TrainCardsDeck);
        deckTrainCards.setText(String.valueOf(deck.getSizeAvailable()));
        deckTrainCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnState.drawFaceDownTrainCard((GameActivity)context);
            }
        });

        final int NUM_TRAIN_CARD_BUTTONS = 5; // Avoids "magic numbers"

        for (int i = 0; i < NUM_TRAIN_CARD_BUTTONS; i++) {
            int resId = getResources().getIdentifier("trainCard" + (i + 1), "id", getPackageName());
            Button trainCardButton = findViewById(resId);
            trainCardButton.setOnClickListener(new TrainCardClickListener(trainCardButton, i));
            trainCardButton.setBackground(updateFaceUpCard(deck.getFaceUpCards().get(i)));
        }
    }

    // SHOULD ONLY BE CALLED BY THE TURN STATES
    public void drawFaceDownTrainCard() {
        if(checkTrainCardDeck()){
            TrainCardDeck deck = data.getGame().getTrainCardDeck();
            TrainCard card = deck.getAvailable().remove(0);
            addTrainCardToPlayer(card);
            deckTrainCards.setText(String.valueOf(deck.getSizeAvailable()));
            updatePlayerDisplay();
        }
        else{
            Toast.makeText(getApplicationContext(), "No available train cards!", Toast.LENGTH_SHORT).show();
        }
    }

    // SHOULD ONLY BE CALLED BY THE TURN STATES
    public void drawFaceUpTrainCard(Button button, int buttonIndex) {
        TrainCard card = data.getGame().getTrainCardDeck().getFaceUpCards().remove(buttonIndex);
        presenter.drawFaceUpTrainCard((GameActivity) context, data.getGame(), card, buttonIndex, button);
        updatePlayerDisplay();
    }

    /*

    Destination Cards

     */

    private void setDestinationCardDeck() {
        String numberOfDestinationCards = Integer.toString(data.getGame().getDestinationCardDeck().getAvailableCards().size());
        String destinationCardButtonText = numberOfDestinationCards + "\nDestination";

        deckDestinationCards = (Button) findViewById(R.id.deckDestinationCards);
        deckDestinationCards.setText(destinationCardButtonText);
        deckDestinationCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnState.drawDestCards((GameActivity)context);
            }
        });
    }

    public void drawDestCard(boolean isFirstTime) {
        Fragment drawDestCardFragment = new DrawDestCardFragment();
        ((DrawDestCardFragment) drawDestCardFragment).setIsFirst(isFirstTime);
        ((DrawDestCardFragment) drawDestCardFragment).setDrawnDestCards(data.getGame().getDestinationCardDeck().drawnThreeCards());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, drawDestCardFragment);
        transaction.commit();

        updatePlayerDisplay();
        updateDestinationCardDeck();
    }

    /*

    MapView functions

     */

    private void setMapView() {
        LinearLayout layout = findViewById(R.id.mapFragmentHolder);
        mapView = new MapView(this, layout.getWidth(), layout.getHeight());

        ViewGroup parent = (ViewGroup)findViewById(R.id.mapFragment).getParent();
        parent.removeAllViews();
        parent.addView(mapView);

        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    int routeID = mapView.onRoute(event.getX(), event.getY());

                    if (routeID != -1) {
                        turnState.claimRoute((GameActivity)context, routeID);
                    }
                }
                return true;
            }
        });
    }

    private void setMapViewOnCreateListener() {
        final View rootView = getWindow().getDecorView().getRootView();
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    boolean onCreated = false;

                    @Override
                    public void onGlobalLayout() {
                        if (!onCreated) {
                            onCreated = true;
                            setMapView();
                        }
                    }
                });
    }

    /*

    Player list setup

     */

    private void setPlayerList(ArrayList<Player> playerList){
        if (playerList == null) {
            return;
        }
        List<String[]> newPlayersList = new ArrayList<>();
        for (Player p : playerList){
            String[] newPlayer = new String[8];
            newPlayer[0] = p.getColor().toString();
            newPlayer[1] = p.getPlayerName();
            newPlayer[2] = Integer.toString(p.getPoints());
            newPlayer[3] = Integer.toString(p.getPosition());
            newPlayer[4] = Integer.toString(p.getTrainCards().size());
            newPlayer[5] = Integer.toString(p.getDestinationCards().size());
            newPlayer[6] = Integer.toString(p.getPlasticTrains());
            newPlayer[7] = Integer.toString(data.getGame().getPlayerTurnIndex()+1);
            newPlayersList.add(newPlayer);
        }
        if (newPlayersList.size() > 0){
            players.clear();
            players.addAll(newPlayersList);
        }
    }

    /*

    Player's Cards

     */

    private void setPlayerTrainCards() {
        RecyclerView trainCardsView = findViewById(R.id.playerTrainCards);
        LinearLayoutManager mgr = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        trainCardsView.setLayoutManager(mgr);
        playerTrainCardsAdapter = new PlayerTrainCardsAdapter(data.getGame().getOnePlayer(data.getUser()).getTrainCards());
        trainCardsView.setAdapter(playerTrainCardsAdapter);
    }

    private void updatePlayerTrainCards() {
        playerTrainCardsAdapter.notifyDataSetChanged();
    }

    public void addTrainCardToPlayer(TrainCard card) {
        Game game = data.getGame();
        String user = data.getUser();

        ArrayList<TrainCard> cards = game.getOnePlayer(user).getTrainCards();
        cards.add(card);
        game.getOnePlayer(user).setTrainCards(cards);

        updatePlayerTrainCards();
    }

    public void updatePlayerDisplay() {
        setPlayerList(data.getGame().getPlayers());

        if (playerListAdapter != null) {
            playerListAdapter.notifyDataSetChanged();
        }

        if (playerTrainCardsAdapter != null) {
            playerTrainCardsAdapter.notifyDataSetChanged();
        }

        if (mapView != null) {
            mapView.invalidate();
        }
    }

    public void updateCardDeck() {
        deckTrainCards.setText(String.valueOf(data.getGame().getTrainCardDeck().getSizeAvailable()));
    }

    public void updateDestinationCardDeck() {
        String update = String.valueOf(data.getGame().getDestinationCardDeck().getAvailableCards().size()) + "\nDestination";
        deckDestinationCards.setText(update);
    }

    public void updateMapView() {
        mapView.invalidate();
    }

    public Button getDeckTrainCards(){ return deckTrainCards; }

    // updates the chosen face up card background
    public Drawable updateFaceUpCard(TrainCard card){
        switch (card.getColor()){
            case Wild:
                return getDrawable(R.drawable.wild_train_card);
            case Red:
                return getDrawable(R.drawable.red_train_card);
            case Blue:
                return getDrawable(R.drawable.blue_train_card);
            case Pink:
                return getDrawable(R.drawable.pink_train_card);
            case Black:
                return getDrawable(R.drawable.black_train_card);
            case Green:
                return getDrawable(R.drawable.green_train_card);
            case White:
                return getDrawable(R.drawable.white_train_card);
            case Orange:
                return  getDrawable(R.drawable.orange_train_card);
            case Yellow:
                return getDrawable(R.drawable.yellow_train_card);
            default: //should be unreachable
                return getDrawable(R.drawable.back_of_train_card);
        }
    }

    // determines whether drawing a train card from the deck is possible
    public boolean checkTrainCardDeck(){
        Game game = data.getGame();
        TrainCardDeck deck = game.getTrainCardDeck();

        if(deck.getAvailable().size() > 0){
            return true;
        }
        else if(deck.getDiscardPile().size() > 0){
            //set the discard pile as the available pile
            deck.setAvailable(deck.getDiscardPile());
            //reset the discard pile
            deck.setDiscardPile(new ArrayList<TrainCard>());
            //shuffle the deck
            deck.shuffle(); // TODO: Card shuffling should be done on the server side so all clients have the same deck
            //reset the deck size
            deckTrainCards.setText(String.valueOf(deck.getSizeAvailable()));
            return true;
        }
        else return false;
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
            Toast.makeText(context.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            return false;
        }

        Route route = data.getAllRoutes().get(routeID);

        TrainColor route_color = route.getTrainColor();

        if (route_color != null) { //
            if (route_color != chosen_color) {
                String error = "The chosen color does not match the color required for this route.";
                Toast.makeText(this,  error, Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        int route_size = route.getSpaces().size();

        Player player = data.getGame().getOnePlayer(data.getUser());
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
            Toast.makeText(this,  error, Toast.LENGTH_SHORT).show();
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

        // TODO: send to server

        updatePlayerDisplay();
    }

    public void endTurn() {
        presenter.endPlayerTurn(data.getGame());
    }

    public void checkIfOurTurn() {
        //System.out.println(turnState.getClass());
        //System.out.println(data.getGame().getOnePlayer(data.getUser()).getPosition());
        //System.out.println(data.getGame().getPlayerTurnIndex());
        if (turnState.getClass().equals(NotMyTurn.class)) {
            Player currentPlayer = data.getGame().getOnePlayer(data.getUser());
            Integer currentPlayerIndex = data.getGame().getPlayerTurnIndex();
            if (currentPlayer.getPosition() == currentPlayerIndex + 1) {
                setTurnState(new MyTurnNoAction());
            }
        }
    }
}
