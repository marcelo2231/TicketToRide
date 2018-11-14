package com.emmettito.tickettoride.views.GameActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * DrawDestCardFragment.java
 *
 * The DrawDestCardFragment is one of the View classes used as part of the game (in the
 * Model-View-Presenter layout). It contains three toggle buttons for selecting cards, three text
 * views for displaying destination cards and one finish button to end the selection process. The
 * user will have three randomly selected destination cards from the deck presented in front of
 * them. They then will have the option of selected any of none of the three cards, which will then
 * be placed into their hand. The class requires a boolean to be set determining if it is the
 * players first time in the game drawing destination cards. If it is, then they must draw at least
 * 2 destination cards. The fragment is created, the boolean set and loaded onto the page in the
 * game activity.
 *
 *
 *
 * Domain:
 *      destCard1: TextView, displays the information about the first destination card
 *      destCard2: TextView, displays the information about the second destination card
 *      destCard3: TextView, displays the information about the third destination card
 *
 *      cardToggle1: ToggleButton, toggles the selection of the first destination card
 *      cardToggle2: ToggleButton, toggles the selection of the first destination card
 *      cardToggle3: ToggleButton, toggles the selection of the first destination card
 *
 *      finishButton: Button, ends the selection process and adds the cards to the players hand
 *
 *      firstTime: Boolean, is set by game activity, determines whether its the first time drawing
 *
 *      mDrawnCard1: DestinationCard, the first destination card to be displayed
 *      mDrawnCard2: DestinationCard, the second destination card to be displayed
 *      mDrawnCard3: DestinationCard, the third destination card to be displayed
 *
 *      toggle1Status: boolean, determines whether the first card has been selected
 *      toggle2Status: boolean, determines whether the second card has been selected
 *      toggle3Status: boolean, determines whether the third card has been selected
 *
 * DrawDestCardFragment extends Fragment.
 *
 * @author  Jason McAllister
 * @since   2018
 *
 *
 */
public class DrawDestCardFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

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

    private OnFragmentInteractionListener mListener;

    /*
     * DrawDestCardFragment is intentionally left blank as pre fragment requirements
     */
    public DrawDestCardFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1 is not used but left for future expansion
     * @param param2 Parameter 2 is not used but left for future expansion
     * @return A new instance of fragment DrawDestCardFragment.
     *
     * @pre none
     * @post not null instance of the fragment
     */
    // TODO: Rename and change types and number of parameters
    public static DrawDestCardFragment newInstance(String param1, String param2) {
        DrawDestCardFragment fragment = new DrawDestCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /*
     * onCreate
     *
     * Overrides the Fragment parent class and allows for the addition of steps at the creation
     * stage of the fragment
     *
     * @param savedInstanceState a bundle that can be used to load in a previous saved state or add
     * additional data at the creation of the object
     *
     * @pre none
     * @post none
     *
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    /*
     * onCreateView
     *
     * This method generates and returns the view which is to be displayed in the fragment manager,
     * called from the activity. It inflates the view, assigns all proper view objects and sets the
     * listeners to each view object.
     *
     * @param inflater The inflater from the object creating the fragment
     * @param container The container from which the fragment is being created and displayed
     * @param savedInstanceState a bundle that can be used to load in a previous saved state or add
     * additional data at the creation of the object
     *
     * @pre The parent has set the isFirst variable and the destination cards using their proper
     * setters
     * @post all view objects are linked to the layout
     * @post all click listeners are set
     * @post the clients hand contains the selected cards, if it is the players first draw, the must
     * select at least two cards
     *
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_draw_dest_card, container, false);

        destCard1 = (TextView) view.findViewById(R.id.destCard1);
        destCard1.setText(mDrawnCard1.toString());
        destCard2 = (TextView) view.findViewById(R.id.destCard2);
        destCard2.setText(mDrawnCard2.toString());
        destCard3 = (TextView) view.findViewById(R.id.destCard3);
        destCard3.setText(mDrawnCard3.toString());

        cardToggle1 = (ToggleButton) view.findViewById(R.id.toggleDest1);
        cardToggle1.setEnabled(true);
        cardToggle1.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                toggle1Status = b;
            }
        });

        cardToggle2 = (ToggleButton) view.findViewById(R.id.toggleDest2);
        cardToggle2.setEnabled(true);
        cardToggle2.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                toggle2Status = b;
            }
        });

        cardToggle3 = (ToggleButton) view.findViewById(R.id.toggleDest3);
        cardToggle3.setEnabled(true);
        cardToggle3.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                toggle3Status = b;
            }
        });

        finishButton = (Button) view.findViewById(R.id.dest_card_button_end);
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
                        Toast.makeText(getActivity(), "Please select at least 2 Destination Cards", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        finalizeSelect(selected, discarded);
                    }
                }
                else {
                    if (selected.size() < 1) {
                        Toast.makeText(getActivity(), "Please select at least 1 Destination Card", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        finalizeSelect(selected, discarded);
                    }
                }

            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    public void finalizeSelect(ArrayList<DestinationCard> selected, ArrayList<DestinationCard> discarded) {
        Client client = Client.getInstance();
        ArrayList<DestinationCard> destCards = client.getGame().getOnePlayer(client.getUser()).getDestinationCards();
        destCards.addAll(selected);
        client.getGame().getOnePlayer(client.getUser()).setDestinationCards(destCards);
        client.getGame().getDestinationCardDeck().addCards(discarded);
        finish();
    }

    /*
     * finish
     *
     * ends the fragment and returns to the activity
     *
     * @pre none
     * @post none
     */
    public void finish() {
        super.onResume();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }


    /*
     * onAttach
     *
     * defines behavior to take place at the moment the fragment is placed on the screen
     *
     * @param context a variable containing the context to be used to attach the fragment to the
     * page
     *
     * @pre none
     * @post none
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    /*
     * onDetach
     *
     * a function defining behavior to be preformed the moment the fragment is removed from the
     * activity
     *
     * @pre none
     * @post none
     *
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * OnFragmentInteractionListener
     *
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     * @pre none
     * @post none
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /**
     * setIsFirst
     *
     * a setter used to set whether its the players first time drawing a dest card
     *
     * @param isFirst a boolean determining if it is the first time or not
     *
     * @pre isFirst must be a valid boolean
     * @post the setter must set the correct value to mIsFirst
     */
    public void setIsFirst(boolean isFirst) {
        this.mFirstTime = isFirst;
    }


    /**
     * setDrawnDestCards
     *
     * sets the destination cards in the fragment to the selected values
     *
     * @param cards a List of size 3 containing the destination cards from the deck
     *
     * @pre cards.size() == 3
     * @post the mDrawnCard variables must all be non null
     */
    public void setDrawnDestCards(List<DestinationCard> cards) {
        if (cards.size() > 2) {
            mDrawnCard1 = cards.get(0);
            mDrawnCard2 = cards.get(1);
            mDrawnCard3 = cards.get(2);
            System.out.println(mDrawnCard3.toString());
        }
    }
}
