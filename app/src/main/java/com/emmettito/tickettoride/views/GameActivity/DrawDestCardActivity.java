package com.emmettito.tickettoride.views.GameActivity;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.GamePresenter;

import java.util.ArrayList;
import java.util.List;

public class DrawDestCardActivity extends AppCompatActivity {

    //true if its the players first time drawing
    private boolean mFirstTime;


    //the destination cards to be displayed and selected from
    private DestinationCard mDrawnCard1;
    private DestinationCard mDrawnCard2;
    private DestinationCard mDrawnCard3;

    //the TextVies to display information about the destination cards
    private TextView destCard1;
    private TextView destCard2;
    private TextView destCard3;

    //the toggle buttons to control the selection of the destination cards
    private ToggleButton cardToggle1;
    private ToggleButton cardToggle2;
    private ToggleButton cardToggle3;

    //The booleans to track whether a card has been selected
    private boolean toggle1Status = false;
    private boolean toggle2Status = false;
    private boolean toggle3Status = false;

    //The button to end the selection process
    private Button finishButton;

    //The presenter used by this view
    private GamePresenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_dest_card);
        Client client = Client.getInstance();
        this.mFirstTime = getIntent().getBooleanExtra("isFirst", false);
        this.presenter = client.getGamePresenter();


        List<DestinationCard> drawnCards = new ArrayList<>();
        if (mFirstTime) {
            drawnCards.add(presenter.drawDestCard(client.getUser()));
            drawnCards.add(presenter.drawDestCard(client.getUser()));
            drawnCards.add(presenter.drawDestCard(client.getUser()));
        } else {
            drawnCards = client.getGame().getDestinationCardDeck().drawnThreeCards();
        }

        this.mDrawnCard1 = drawnCards.get(0);
        this.mDrawnCard2 = drawnCards.get(1);
        this.mDrawnCard3 = drawnCards.get(2);

//        this.mDrawnCard1 = (DestinationCard) savedInstanceState.getSerializable("card1");
//        this.mDrawnCard2 = (DestinationCard) savedInstanceState.getSerializable("card2");
//        this.mDrawnCard3 = (DestinationCard) savedInstanceState.getSerializable("card3");
//        this.presenter = (GamePresenter) savedInstanceState.getSerializable("presenter");


        destCard1 = (TextView) findViewById(R.id.destCard1);
        destCard1.setText(mDrawnCard1.toString());
        destCard2 = (TextView) findViewById(R.id.destCard2);
        destCard2.setText(mDrawnCard2.toString());
        destCard3 = (TextView) findViewById(R.id.destCard3);
        destCard3.setText(mDrawnCard3.toString());

        cardToggle1 = (ToggleButton) findViewById(R.id.toggleDest1);
        cardToggle1.setEnabled(true);
        cardToggle1.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                toggle1Status = b;
            }
        });

        cardToggle2 = (ToggleButton) findViewById(R.id.toggleDest2);
        cardToggle2.setEnabled(true);
        cardToggle2.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                toggle2Status = b;
            }
        });

        cardToggle3 = (ToggleButton) findViewById(R.id.toggleDest3);
        cardToggle3.setEnabled(true);
        cardToggle3.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                toggle3Status = b;
            }
        });

        finishButton = (Button) findViewById(R.id.dest_card_button_end);
        finishButton.setEnabled(true);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send the unselected cards to the server
                ArrayList<DestinationCard> selected = new ArrayList<>();
                ArrayList<DestinationCard> discarded = new ArrayList<>();
                if (toggle1Status) {
                    selected.add(mDrawnCard1);
                }
                else {
                    discarded.add(mDrawnCard1);
                }
                if (toggle2Status) {
                    selected.add(mDrawnCard2);
                }
                else {
                    discarded.add(mDrawnCard2);
                }
                if (toggle3Status) {
                    selected.add(mDrawnCard3);
                }
                else {
                    discarded.add(mDrawnCard3);
                }
                for (int i = 0; i < selected.size(); i++) {
                    Log.w("destCard", selected.get(i).toString());
                }

                if (mFirstTime) {
                    if (selected.size() < 2) {
                        Toast.makeText(getApplicationContext(), "Please select at least 2 Destination Cards (first turn rule)", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        finalizeSelect(selected, discarded);
                    }
                }
                else {
                    if (selected.size() < 1) {
                        Toast.makeText(getApplicationContext(), "Please select at least 1 Destination Card", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        finalizeSelect(selected, discarded);
                    }
                }

            }
        });
    }

    public void finalizeSelect(ArrayList<DestinationCard> selected, ArrayList<DestinationCard> discarded) {
        Client client = Client.getInstance();
        ArrayList<DestinationCard> destCards = client.getGame().getOnePlayer(client.getUser()).getDestinationCards();
        destCards.addAll(selected);


        if (!mFirstTime) {
            client.getGame().getOnePlayer(client.getUser()).setDestinationCards(destCards);
            client.getGame().getDestinationCardDeck().addCards(discarded);
        }
        else {
            for (int i = 0; i < discarded.size(); i++) {
                presenter.discardDestCard(client.getUser(), discarded.get(i).getCardID());
            }
        }

        presenter.setGame(client.getGame());

        finish();
    }
    /**
     * onBackPressed
     *
     * This method defines the behavior of the Android back button and is inherited and overridden
     * from the FragmentActivity parent class. This implementation disables the back button.
     *
     * @pre None
     *
     * @post None
     *
     *
     */
    @Override
    public void onBackPressed() {}


    public void setIsFirst(boolean isFirst) {
        this.mFirstTime = isFirst;
    }

    public void setDrawnDestCards(List<DestinationCard> cards) {
        if (cards.size() > 2) {
            mDrawnCard1 = cards.get(0);
            mDrawnCard2 = cards.get(1);
            mDrawnCard3 = cards.get(2);
            //System.out.println(mDrawnCard3.toString());
        }
    }

    public void setPresenter(GamePresenter presenter) {
        this.presenter = presenter;
    }

}
