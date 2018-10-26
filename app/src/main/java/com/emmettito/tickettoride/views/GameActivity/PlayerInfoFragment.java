package com.emmettito.tickettoride.views.GameActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emmettito.tickettoride.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerInfoFragment extends Fragment {

    /** Fields **/
    private RecyclerView recycle;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public PlayerInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_info, container, false);


        recycle = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        recycle.setLayoutManager(mLayoutManager);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
