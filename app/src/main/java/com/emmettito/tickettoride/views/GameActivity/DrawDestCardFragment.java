package com.emmettito.tickettoride.views.GameActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.emmettito.tickettoride.R;

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

    private TextView destCard1;
    private TextView destCard2;
    private TextView destCard3;

    private ToggleButton cardToggle1;
    private ToggleButton cardToggle2;
    private ToggleButton cardToggle3;

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
        destCard1 = (TextView) getView().findViewById(R.id.destCard1);
        destCard2 = (TextView) getView().findViewById(R.id.destCard2);
        destCard3 = (TextView) getView().findViewById(R.id.destCard3);

        cardToggle1 = (ToggleButton) getView().findViewById(R.id.toggleDest1);
        cardToggle2 = (ToggleButton) getView().findViewById(R.id.toggleDest2);
        cardToggle3 = (ToggleButton) getView().findViewById(R.id.toggleDest3);

        finishButton = (Button) getView().findViewById(R.id.dest_card_button_end);
        finishButton.setEnabled(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_draw_dest_card, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public void drawDestCards() {

    }
}
