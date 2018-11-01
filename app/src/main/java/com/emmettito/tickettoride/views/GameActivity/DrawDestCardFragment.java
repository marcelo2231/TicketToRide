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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DrawDestCardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DrawDestCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrawDestCardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean mFirstTime;

    private DestinationCard mDrawnCard1;
    private DestinationCard mDrawnCard2;
    private DestinationCard mDrawnCard3;

    private TextView destCard1;
    private TextView destCard2;
    private TextView destCard3;

    private ToggleButton cardToggle1;
    private ToggleButton cardToggle2;
    private ToggleButton cardToggle3;

    private boolean toggle1Status = false;
    private boolean toggle2Status = false;
    private boolean toggle3Status = false;

    private Button finishButton;

    private OnFragmentInteractionListener mListener;

    public DrawDestCardFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrawDestCardFragment.
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

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
                        Client client = Client.getInstance();
                        client.addDestCards(selected);
                        finish();
                    }
                }
                else {
                    if (selected.size() < 1) {
                        Toast.makeText(getActivity(), "Please select at least 1 Destination Card", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Client client = Client.getInstance();
                        client.addDestCards(selected);
                        finish();
                    }
                }

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void finish() {
        super.onResume();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setIsFirst(boolean isFirst) {
        this.mFirstTime = isFirst;
    }

    public void setDrawnDestCards(List<DestinationCard> cards) {
        if (cards.size() > 2) {
            mDrawnCard1 = cards.get(0);
            mDrawnCard2 = cards.get(1);
            mDrawnCard3 = cards.get(2);
            System.out.println(mDrawnCard3.toString());
        }
    }
}
