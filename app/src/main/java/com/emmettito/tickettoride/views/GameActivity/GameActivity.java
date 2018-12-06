package com.emmettito.tickettoride.views.GameActivity;

import android.app.Activity;
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
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainCardComparator;
import com.emmettito.models.Cards.TrainCardDeck;
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.models.CommandModels.Command;
import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.GamePresenter;
import com.emmettito.tickettoride.views.FinalResultsActivity.FinalResultsActivity;
import com.emmettito.tickettoride.views.GameActivity.Turns.MyTurnNoAction;
import com.emmettito.tickettoride.views.GameActivity.Turns.NotMyTurn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends FragmentActivity implements DestCardDisplayFragment.OnFragmentInteractionListener {

    private GamePresenter presenter = new GamePresenter(this);

    public final static int DRAW_DEST_CARD_ACTIVITY = 69;

    private Client data;

    private Button deckTrainCards;
    private Button deckDestinationCards;

    private List<String[]> players = new ArrayList<>();
    private RecyclerView.Adapter playerListAdapter;
    private RecyclerView.Adapter playerTrainCardsAdapter;

    private boolean isRunning;

    private  MapView mapView = null;

    private GameActivity thisGameActivity;

    final Handler timerHandler = new Handler();
    final Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isRunning) {
                return;
            }
            thisGameActivity.checkIfOurTurn();
            thisGameActivity.updatePlayerDisplay();
            thisGameActivity.updateDestinationCardDeck();
            thisGameActivity.updateCardDeck();

            thisGameActivity.updatePlayerTrainCards();
            thisGameActivity.updateMapView();
            thisGameActivity.updateFaceUpCards();
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

        //System.out.println(playerList);

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

        //presenter.addObserver();
        presenter.startPoller();

        //after setting up/inflating, initialize the game-starting processes
        if (!playerList.get(data.getPlayerID()).isHasPickedDestinationCards()){
            playerList.get(data.getPlayerID()).setHasPickedDestinationCards(true);
            drawDestCard(true);
        }

        timerHandler.postDelayed(timerRunnable, 500);
        isRunning = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case DRAW_DEST_CARD_ACTIVITY:
                //you just got back from activity B - deal with resultCode
                //use data.getExtra(...) to retrieve the returned data
                if (data == null) {
                    break;
                }
                if (!data.getBooleanExtra("isFirst", false)) {
                    createDialog("Your Turn Is Over.");
                    presenter.setTurnState(new NotMyTurn());
                    presenter.endTurn();
                }
                else {
                    //startGame();
                }
                //presenter.startPoller();

                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    @Override
    public void onBackPressed() {}

    @Override
    public void onResume() {
        super.onResume();
        //data.setGame(presenter.getGame());
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
                presenter.enterChat();
            }
        });

        Button leaveGameButton = findViewById(R.id.leaveGameButton);
        leaveGameButton.setEnabled(true);
        leaveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerHandler.removeCallbacks(timerRunnable);
                isRunning = false;
                presenter.shutDownPoller();
                presenter.leaveGame();
            }
        });

        Button displayCommandsButton = findViewById(R.id.displayCommandsButton);
        displayCommandsButton.setEnabled(true);
        displayCommandsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.viewCommands();
            }
        });

        Button viewDestinationCardsButton = findViewById(R.id.viewDestinationCardsButton);
        viewDestinationCardsButton.setEnabled(true);
        viewDestinationCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.viewDestCard();
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

        private TrainCardClickListener(Button trainCardButton, int buttonIndex) {
            this.trainCardButton = trainCardButton;
            this.buttonIndex = buttonIndex;
        }

        @Override
        public void onClick(View v) {
            presenter.drawFaceUpTrainCard(trainCardButton, buttonIndex);
        }
    }

    private void setTrainCardDeck() {
        TrainCardDeck deck = data.getGame().getTrainCardDeck();

        deckTrainCards = findViewById(R.id.TrainCardsDeck);
        deckTrainCards.setText(String.valueOf(deck.getSizeAvailable()));
        deckTrainCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.drawFaceDownTrainCard();
            }
        });

        updateFaceUpCards();
    }

    // SHOULD ONLY BE CALLED BY THE TURN STATES
    public boolean drawFaceDownTrainCard() {
        try {
            TrainCardDeck deck = data.getGame().getTrainCardDeck();
            if (deck.getAvailable().size() == 0){
                deck.setAvailable(deck.getDiscardPile());
                deck.shuffle();
                if (deck.getAvailable().size() == 0){
                    throw new IndexOutOfBoundsException("Deck is empty");
                }
            }
            TrainCard card = deck.getAvailable().remove(0);
            //presenter.drawTrainCard((GameActivity) context, card);
            addTrainCardToPlayer(card);

            String aan = "a";
            if (card.getColor() == TrainColor.Orange) {
                aan = "an";
            }
            Toast.makeText(this, "You drew " + aan + " " + card.getColor().toString().toLowerCase() + " train card", Toast.LENGTH_SHORT).show();

            Collections.sort(data.getGame().getOnePlayer(data.getUser()).getTrainCards(), new TrainCardComparator());
            Result result = presenter.setGame(data.getGame());

            String description = "Drew face down train card with id " + card.getCardID() + " and color " + card.getColor().toString() + ".";
            Command command = new Command(data.getUser(), "DrawFaceDownTrainCard", description, "", "");
            //gameDatabase.addCommand(commandModel.getGameName(), command);

            Game game = data.getGame();
            ArrayList<Command> commands = game.getCommands();
            commands.add(command);
            game.setCommands(commands);
            game.setTrainCardDeck(deck);
            data.setGame(game);
            presenter.setGame(data.getGame());

            //Do something with result maybe
        } catch (IndexOutOfBoundsException e) {
            notifyDeckEmpty();
            return false;
        }
        return true;
    }

    // SHOULD ONLY BE CALLED BY THE TURN STATES
    public void drawFaceUpTrainCard(Button button, int buttonIndex) {
        TrainCard card = data.getGame().getTrainCardDeck().getFaceUpCards().get(buttonIndex);
        //decrement the number of wilds if the drawn card is a wild
        if(card.getColor()== TrainColor.Wild){
            int numWilds = data.getGame().getTrainCardDeck().getNumFaceUpWilds();
            data.getGame().getTrainCardDeck().setNumFaceUpWilds(--numWilds);
        }
        TrainCard newCard;

        if (checkTrainCardDeck()) {
            newCard = data.getGame().getTrainCardDeck().getAvailable().remove(0);
            //increment the number of faceUp wilds if the new card is a wild
            if(newCard.getColor() == TrainColor.Wild){
                int numWilds = data.getGame().getTrainCardDeck().getNumFaceUpWilds();
                numWilds++;
                data.getGame().getTrainCardDeck().setNumFaceUpWilds(numWilds);
            }
        }
        else {
            newCard = null;
        }
        data.getGame().getTrainCardDeck().getFaceUpCards().set(buttonIndex, newCard);
        addTrainCardToPlayer(card);

        Collections.sort(data.getGame().getOnePlayer(data.getUser()).getTrainCards(), new TrainCardComparator());

        String description = "Drew face up train card with position " + buttonIndex + ", id " + card.getCardID() + ", and color " + card.getColor().toString() + ".";
        Command command = new Command(data.getUser(), "DrawFaceUpTrainCard", description, "", "");
        //gameDatabase.addCommand(commandModel.getGameName(), command);

        data.getGame().getCommands().add(command);
        presenter.setGame(data.getGame());

        updatePlayerDisplay();
        updateFaceUpCards();
        //presenter.setGame(data.getGame());

        //if there's more than 3 wilds face-up, tell the server to shuffle them
        if(data.getGame().getTrainCardDeck().getNumFaceUpWilds() >= 3) {
            Toast.makeText(getApplicationContext(), "3 face-up wild cards; shuffling them back in", Toast.LENGTH_SHORT).show();
            shuffleFaceUpCards();
        }
        System.out.println("number of wilds after shuffling = " + data.getGame().getTrainCardDeck().getNumFaceUpWilds());
    }

    private void shuffleFaceUpCards() {
        if(presenter.shuffleFaceUpCards()){
            Toast.makeText(getApplicationContext(), "Successfully re-shuffled the face-up cards", Toast.LENGTH_SHORT).show();
            updateFaceUpCards();
        }
        else{
            Toast.makeText(getApplicationContext(), "Couldn't shuffle the face-up cards", Toast.LENGTH_SHORT).show();
        }
    }

    public void notifyDeckEmpty() {
        Toast.makeText(getApplicationContext(), "No available train cards!", Toast.LENGTH_SHORT).show();
    }

    /*

    Destination Cards

     */

    private void setDestinationCardDeck() {
        String numberOfDestinationCards = Integer.toString(data.getGame().getDestinationCardDeck().getAvailableCards().size());
        String destinationCardButtonText = numberOfDestinationCards;

        deckDestinationCards = (Button) findViewById(R.id.deckDestinationCards);
        deckDestinationCards.setText(destinationCardButtonText);
        deckDestinationCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean successful = presenter.drawDestCards();

                if (!successful) {
                    Toast.makeText(getApplicationContext(), "Error: Could not connect to server. Can't draw Destination Cards.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void drawDestCard(boolean isFirstTime) {
        data.setGamePresenter(presenter);
        if (data.getGame().getDestinationCardDeck().size() < 3) {
            Toast.makeText(getApplicationContext(), "No available destination cards!", Toast.LENGTH_SHORT).show();
        }
        else {
            Activity drawDestCardActivity = new DrawDestCardActivity();
            Bundle bundle = new Bundle();
            Intent intent = new Intent(getApplicationContext(), DrawDestCardActivity.class);
//            ((DrawDestCardActivity) DrawDestCardActivity).setIsFirst(isFirstTime);
            List<DestinationCard> drawnCards = new ArrayList<>();
            intent.putExtra("isFirst", isFirstTime);

//            ((DrawDestCardActivity) DrawDestCardActivity).setPresenter(presenter);
            //((DrawDestCardActivity) drawDestCardActivity).setPresenter(presenter);

            //bundle.putSerializable("presenter", presenter);


            //Draw three cards from the server
//            if (isFirstTime) {
//                drawnCards.add(presenter.drawDestCard(data.getUser()));
//                drawnCards.add(presenter.drawDestCard(data.getUser()));
//                drawnCards.add(presenter.drawDestCard(data.getUser()));
//            } else {
//                drawnCards = data.getGame().getDestinationCardDeck().drawnThreeCards();
//            }
//            for(int i = 0; i < drawnCards.size(); i++) {
//                String string = "card" + i;
//                bundle.putSerializable(string, drawnCards.get(i));
//            }

//            ((DrawDestCardActivity) DrawDestCardActivity).setDrawnDestCards(drawnCards);
            //((DrawDestCardActivity) drawDestCardActivity).setDrawnDestCards(drawnCards);
//

//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(android.R.id.content, DrawDestCardActivity);
//            transaction.commit();

            //intent.putExtras(bundle);
//            startActivity(intent);
            startActivityForResult(intent, DRAW_DEST_CARD_ACTIVITY);

            //presenter.setGame(data.getGame());

            updatePlayerDisplay();
            updateDestinationCardDeck();
        }
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
    }

    public void claimRoute(int routeID) {
        presenter.claimRoute(routeID);
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
        playerTrainCardsAdapter = new PlayerTrainCardsAdapter(data.getGame().getOnePlayer(data.getUser()).getIndexedCards());
        trainCardsView.setAdapter(playerTrainCardsAdapter);
    }

    private void updatePlayerTrainCards() {
        Player player = data.getGame().getOnePlayer(data.getUser());
        player.reIndexCards();
        playerTrainCardsAdapter.notifyDataSetChanged();
    }

    public void addTrainCardToPlayer(TrainCard card) {
        Game game = data.getGame();
        String user = data.getUser();

        ArrayList<TrainCard> cards = game.getOnePlayer(user).getTrainCards();
        cards.add(card);
        Player player = game.getOnePlayer(user);
        player.setTrainCards(cards);
        //game.setOnePlayer(player);
        //data.setGame(game);

        updatePlayerTrainCards();
    }

    public void updatePlayerDisplay() {
        setPlayerList(data.getGame().getPlayers());

        if (playerListAdapter != null) {
            playerListAdapter.notifyDataSetChanged();
        }

        if (playerTrainCardsAdapter != null) {
            Player player = data.getGame().getOnePlayer(data.getUser());
            player.reIndexCards();
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
        String update = String.valueOf(data.getGame().getDestinationCardDeck().getAvailableCards().size());
        deckDestinationCards.setText(update);
    }

    public void updateMapView() {
        if(mapView != null) {
            mapView.invalidate();
        }
    }

    public Button getDeckTrainCards(){ return deckTrainCards; }

    public void updateFaceUpCards() {
        TrainCardDeck deck = data.getGame().getTrainCardDeck();
        final int NUM_TRAIN_CARD_BUTTONS = 5; // Avoids "magic numbers"

        for (int i = 0; i < NUM_TRAIN_CARD_BUTTONS; i++) {
            int resId = getResources().getIdentifier("trainCard" + (i + 1), "id", getPackageName());
            Button trainCardButton = findViewById(resId);
            trainCardButton.setOnClickListener(new TrainCardClickListener(trainCardButton, i));
            Drawable background = null;
            if(deck.getFaceUpCards().size() <= i){
                trainCardButton.setBackgroundColor(0x00);
                trainCardButton.setBackgroundResource(android.R.drawable.btn_default);
                trainCardButton.setEnabled(false);
                trainCardButton.setBackground(null);
                return;
            }
            if (deck.getFaceUpCards().get(i) != null) {
                background = updateFaceUpCard(deck.getFaceUpCards().get(i));
            }

            if (background == null) {
                trainCardButton.setBackgroundColor(0x00);
                trainCardButton.setBackgroundResource(android.R.drawable.btn_default);
                trainCardButton.setEnabled(false);
                continue;
            }
            trainCardButton.setBackground(background);
        }
    }

    // updates the chosen face up card background
    public Drawable updateFaceUpCard(TrainCard card){
        if (card == null) {
            return null;
        }
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

            game.setTrainCardDeck(deck);

            return true;
        }
        else return false;
    }

    public void endGame() {
        //data.getGame().setPlayerTurnIndex(presenter.endPlayerTurn(data.getGame()));
        //presenter.startPoller();

        timerHandler.removeCallbacks(timerRunnable);
        isRunning = false;

        presenter.shutDownPoller();

        System.out.println("And here");

        data.getGame().setGameOver(true);

        presenter.setGame(data.getGame());

        presenter.endGame();

        System.out.println("I got here");

        Intent intent = new Intent(getApplicationContext(), FinalResultsActivity.class);
        startActivity(intent);
        finish();
    }

    public void checkIfOurTurn() {
        //System.out.println(turnState.getClass());
        //System.out.println(data.getGame().getOnePlayer(data.getUser()).getPosition());
        //System.out.println(data.getGame().getPlayerTurnIndex());

        if (data.getGame().isGameOver()) {
            endGame();
            return;
        }

        if (presenter.getTurnState().getClass().equals(NotMyTurn.class)) {
            Player currentPlayer = data.getGame().getOnePlayer(data.getUser());
            Integer currentPlayerIndex = data.getGame().getPlayerTurnIndex();
            if (currentPlayer.getPosition() == currentPlayerIndex + 1) {
                if (data.getGame().isLastTurn()) {
                    if (data.getGame().getEndingPlayer().equals(data.getUser())) {
                        endGame();
                        return;
                    }
                    else {
                        Toast.makeText(this, "Final Round", Toast.LENGTH_LONG).show();
                    }
                }
                presenter.setTurnState(new MyTurnNoAction());
                //presenter.shutDownPoller();
                createDialog("Your Turn");
            }
        }
    }

    public void createDialog(String message) {
        /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GameActivity.this);

        AlertDialog dialog = alertDialogBuilder.setMessage(message).
                setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Close window without doing anything else
                    }
                }).create();

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.show();*/

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public GamePresenter getPresenter() {
        return presenter;
    }

    //public void addCommand(String description) {
        //String description = "Drew train card with id " + card.getCardID() + " and color " + card.getColor().toString() + ".";
        //String requestJson = new Serializer().serialize(commandModel);
        //String resultJson = new Serializer().serialize(result);
        //Command command = new Command(commandModel.getPlayerName(), "DrawTrainCard", description, requestJson, resultJson);
        //gameDatabase.addCommand(commandModel.getGameName(), command);
    //}
}
