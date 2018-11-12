package com.emmettito.tickettoride.views.GameActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.GamePresenter;
import com.emmettito.tickettoride.views.GameActivity.Turns.MyTurnNoAction;
import com.emmettito.tickettoride.views.GameActivity.Turns.NotMyTurn;
import com.emmettito.tickettoride.views.GameActivity.Turns.Turn;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends FragmentActivity implements DrawDestCardFragment.OnFragmentInteractionListener, DestCardDisplayFragment.OnFragmentInteractionListener {

    private final int NUM_TRAIN_CARD_BUTTONS = 5;

    public class TrainCardClickListener implements View.OnClickListener {

        private Button trainCardButton;
        private int buttonIndex;

        public TrainCardClickListener(Button trainCardButton, int buttonIndex) {
            this.trainCardButton = trainCardButton;
            this.buttonIndex = buttonIndex;
        }

        @Override
        public void onClick(View v) {
            tempCommands.add(game.getPlayers().get(game.getPlayerTurnIndex()).getPlayerName() + ": Draw Train Card Command");
            TrainCard card = game.getTrainCardDeck().getFaceUpCards().remove(buttonIndex);
            presenter.drawFaceUpTrainCard((GameActivity) context, game, card, buttonIndex, trainCardButton);
            updatePlayerDisplay();
        }
    }

    private Game game;
    private Button chatButton;
    private GamePresenter presenter = new GamePresenter(this);
    private Turn turnState;
    private Button deckTrainCards;
    private Button deckDestinationCards;
    private Button viewDestinationCardsButton;
    private Button leaveGameButton;
    private Button displayCommandsButton;
    private RecyclerView playerListRecycle;
    private RecyclerView.Adapter playerListAdapter;
    private RecyclerView.LayoutManager playerListLayoutManager;
    private List<String[]> players = new ArrayList<>();
    private Client data;
    private Context context = this;
    private ArrayList<String> tempCommands = new ArrayList<>();


    // MAP VARIABLES
    private int map_width = 0;
    private int map_height = 0;
    private  MapView mapView = null;

    // TRAINCARD VARIABLES
    private RecyclerView.Adapter playerTrainCardsAdapter;

    private GameActivity mGameActivity;

    public Button getDeckTrainCards(){ return deckTrainCards; }

    public Turn getTurnState() {
        return turnState;
    }

    public void setTurnState(Turn turnState) {
        this.turnState = turnState;
    }

    public void enterChat() {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        startActivity(intent);
    }

    public void leaveGame() {
        Toast.makeText(getApplicationContext(), "Leaving game", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void viewDestCard() {
        Fragment displayDestCardFragment = new DestCardDisplayFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, displayDestCardFragment);
        transaction.commit();
        //Toast.makeText(v.getContext(), "Open a view to see the player's destination cards", Toast.LENGTH_SHORT).show();
        game.getOnePlayer(data.getUser()).getDestinationCards();
        game.getPlayers().get(game.getPlayerTurnIndex()).setTrainCards(game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards());
        updatePlayerDisplay();
        updateDestinationCardDeck();
    }

    public void viewCommands() {
        Intent intent = new Intent(getApplicationContext(), GameHistoryActivity.class);
        startActivity(intent);
    }

    public boolean canClaimRoute(int routeID) {
        return false;
    }

    public void claimRoute(int routeID) {
        //data.addToTakenRoutes(val);
        //mapView.invalidate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_game);

        mGameActivity = this;
        data = Client.getInstance();
        game = presenter.getGame();
        // Get players
        final ArrayList<Player> playerList = presenter.getPlayers();
        setupPlayerList(playerList);
        game.setPlayers(playerList);

        setMapViewOnCreateListener();
        setPlayerTrainCards();
        setGameButtons();
        setTrainCardDeck();
        setDestinationCardDeck();

        /** Set up recycler view **/
        playerListRecycle = (RecyclerView) findViewById(R.id.playerListView);
        playerListLayoutManager = new LinearLayoutManager(this);
        playerListRecycle.setLayoutManager(playerListLayoutManager);
        playerListAdapter = new PlayerInfoAdapter(players);
        playerListRecycle.setAdapter(playerListAdapter);


        //activate the draw card buttons if it's the player's turn
        if(game.isPlayerTurn(game.getOnePlayer(data.getUser()))){
            turnState = new MyTurnNoAction();

            deckTrainCards.setEnabled(true);
            deckDestinationCards.setEnabled(true);
        }
        else{
            turnState = new NotMyTurn();

            deckTrainCards.setEnabled(false);
            deckDestinationCards.setEnabled(false);
        }
        presenter.addGame(game);

        //after setting up/inflating, initialize the game-starting processes
        drawDestCard(true);
        startGame();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();

        game.getOnePlayer(data.getUser()).setDestinationCards((ArrayList) data.getPlayerDestCards());
        game.getPlayers().get(game.getPlayerTurnIndex()).setTrainCards(game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards());
        updatePlayerDisplay();
        updateDestinationCardDeck();
    }

    public void drawDestCard(boolean isFirstTime) {
        tempCommands.add("Draw Destination Card Command");
        Fragment drawDestCardFragment = new DrawDestCardFragment();
        ((DrawDestCardFragment) drawDestCardFragment).setIsFirst(isFirstTime);
        ((DrawDestCardFragment) drawDestCardFragment).setDrawnDestCards(game.getDestinationCardDeck().drawnThreeCards());

        //DestinationCardDeck deck = game.getDestinationCardDeck();
        //List<DestinationCard> drawnCards = deck.drawnThreeCards();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, drawDestCardFragment);
        transaction.commit();

        game.getOnePlayer(data.getUser()).setDestinationCards(data.getPlayerDestCards());
        //System.out.printf("This is the number of destination cards: %d", game.getOnePlayer(data.getUser()).getDestinationCards().size());
        game.getPlayers().get(game.getPlayerTurnIndex()).setTrainCards(game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards());
        updatePlayerDisplay();
        updateDestinationCardDeck();
    }


    private void startGame(){
        //assign each player a color: DONE
        //determine player order: DONE
        //have the server randomly select select 4 train cards for each player
        //have the server select 3 destination cards for each player.
            //and allow the player to discard 0 or 1 of them

        tempCommands.add("Start Game Command: Player order was decided, player color was added");
        tempCommands.add("Draw 3 DestCardsFor each player Command");

        game.getOnePlayer(data.getUser()).setDestinationCards((ArrayList) data.getPlayerDestCards());
        game.getPlayers().get(game.getPlayerTurnIndex()).setTrainCards(game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards());
        updatePlayerDisplay();
        updateDestinationCardDeck();

        ArrayList<TrainCard> currentCards = game.getOnePlayer(data.getUser()).getTrainCards();

        for (int i = 0; i < currentCards.size(); i++) {
            addTrainCardToPlayer(currentCards.get(i));
        }
    }


    /*
    Main Game Buttons
     */

    private void setGameButtons() {
        chatButton = findViewById(R.id.openChatButton);
        chatButton.setEnabled(true);
        chatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                turnState.enterChat((GameActivity)context);
            }
        });

        leaveGameButton = findViewById(R.id.leaveGameButton);
        leaveGameButton.setEnabled(true);
        leaveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnState.leaveGame((GameActivity)context);
            }
        });

        displayCommandsButton = findViewById(R.id.displayCommandsButton);
        displayCommandsButton.setEnabled(true);
        displayCommandsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnState.viewCommands((GameActivity)context);
            }
        });

        viewDestinationCardsButton = (Button) findViewById(R.id.viewDestinationCardsButton);
        viewDestinationCardsButton.setEnabled(true);
        viewDestinationCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnState.viewDestCard((GameActivity)context);
            }
        });
    }

    private void setTrainCardDeck() {
        deckTrainCards = findViewById(R.id.TrainCardsDeck);
        deckTrainCards.setText(String.valueOf(game.getTrainCardDeck().getSizeAvailable()));
        deckTrainCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Drawing train card", Toast.LENGTH_SHORT).show();
                //helper function that makes sure there's cards in the deck
                if(checkTrainCardDeck()){
                    tempCommands.add(game.getPlayers().get(game.getPlayerTurnIndex()).getPlayerName() + ": Draw Train Card Command");

                    TrainCard card = game.getTrainCardDeck().getAvailable().remove(0);
                    game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards().add(card);
                    addTrainCardToPlayer(card);

//                    game.getPlayers().get(game.getPlayerTurnIndex()).setTrainCards(game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards());
                    deckTrainCards.setText(String.valueOf(game.getTrainCardDeck().getSizeAvailable()));
                    updatePlayerDisplay();
                }
                else{
                    Toast.makeText(v.getContext(), "No available train cards!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        for (int i = 0; i < NUM_TRAIN_CARD_BUTTONS; i++) {
            int resId = getResources().getIdentifier("trainCard" + (i + 1), "id", getPackageName());
            Button trainCardButton = findViewById(resId);
            trainCardButton.setOnClickListener(new TrainCardClickListener(trainCardButton, i));
            trainCardButton.setBackground(updateFaceUpCard(game.getTrainCardDeck().getFaceUpCards().get(i)));
        }
    }

    private void setDestinationCardDeck() {
        String numberOfDestinationCards = Integer.toString(game.getDestinationCardDeck().getAvailableCards().size());
        String destinationCardButtonText = numberOfDestinationCards + "\nDestination";

        deckDestinationCards = (Button) findViewById(R.id.deckDestinationCards);
        deckDestinationCards.setText(destinationCardButtonText);
        deckDestinationCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnState.drawDestCards((GameActivity)context);
                Toast.makeText(v.getContext(), "Drawing 3 destination cards", Toast.LENGTH_SHORT).show();
                drawDestCard(false);
                //System.out.printf("This is the number of destination cards: %d", game.getOnePlayer(data.getUser()).getDestinationCards().size());
                game.getOnePlayer(data.getUser()).setDestinationCards((ArrayList) data.getPlayerDestCards());
                //System.out.printf("This is the number of destination cards: %d", game.getOnePlayer(data.getUser()).getDestinationCards().size());
                game.getPlayers().get(game.getPlayerTurnIndex()).setTrainCards(game.getPlayers().get(game.getPlayerTurnIndex()).getTrainCards());
                updatePlayerDisplay();
                updateDestinationCardDeck();
                //System.out.printf("This is the number of destination cards: %d", game.getOnePlayer(data.getUser()).getDestinationCards().size());

            }
        });
    }


    /*
    MapView functions
     */

    private void setMapDimensions() {
        LinearLayout layout = findViewById(R.id.mapFragmentHolder);
        map_width = layout.getWidth();
        map_height = layout.getHeight();
    }

    private void setMapView() {
        mapView = new MapView(this, map_width, map_height, game);

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
                            setMapDimensions();
                            setMapView();
                        }
                    }
                });
    }

    /*
    Player's Train Cards
     */

    private void setPlayerTrainCards() {
        RecyclerView trainCardsView = findViewById(R.id.playerTrainCards);
        LinearLayoutManager mgr = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        trainCardsView.setLayoutManager(mgr);
        playerTrainCardsAdapter = new PlayerTrainCardsAdapter(data.getPlayerTrainCards());
        trainCardsView.setAdapter(playerTrainCardsAdapter);
    }

    private void updatePlayerTrainCards() {
        playerTrainCardsAdapter.notifyDataSetChanged();
    }

    public void addTrainCardToPlayer(TrainCard card) {
        data.addTrainCard(card);
        updatePlayerTrainCards();
    }

    public void removeTrainCardFromPlayer(TrainCard card) {
        data.removeTrainCard(card);
        updatePlayerTrainCards();
    }

    /*
    Player's Destination Cards
     */


    /*

     */

    public void updatePlayerDisplay() {
        setupPlayerList(game.getPlayers());

        if (playerListAdapter == null) {
            return;
        }

        playerListAdapter.notifyDataSetChanged();
    }

    public void updateCardDeck() {
        deckTrainCards.setText(String.valueOf(game.getTrainCardDeck().getSizeAvailable()));
    }

    public void updateDestinationCardDeck() {
        String update = String.valueOf(game.getDestinationCardDeck().getAvailableCards().size()) + "\nDestination";
        deckDestinationCards.setText(update);
    }

    private void setupPlayerList(ArrayList<Player> playerList){
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
            newPlayer[7] = Integer.toString(game.getPlayerTurnIndex()+1);
            newPlayersList.add(newPlayer);
        }
        if (newPlayersList.size() > 0){
            players.clear();
            players.addAll(newPlayersList);
        }
    }

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
        if(game.getTrainCardDeck().getAvailable().size() > 0){
            return true;
        }
        else if(game.getTrainCardDeck().getDiscardPile().size() > 0){
            //set the discard pile as the available pile
            game.getTrainCardDeck().setAvailable(game.getTrainCardDeck().getDiscardPile());
            //reset the discard pile
            game.getTrainCardDeck().setDiscardPile(new ArrayList<TrainCard>());
            //shuffle the deck
            game.getTrainCardDeck().shuffle();
            //reset the deck size
            deckTrainCards.setText(String.valueOf(game.getTrainCardDeck().getSizeAvailable()));
            return true;
        }
        else return false;
    }
}
