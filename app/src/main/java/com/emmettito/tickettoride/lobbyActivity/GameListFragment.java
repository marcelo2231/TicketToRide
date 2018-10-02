package com.emmettito.tickettoride.lobbyActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.LobbyPresenter;

import java.util.Observable;
import java.util.Observer;

public class GameListFragment extends Fragment implements LobbyPresenter.lobbyView, Observer {

    private RecyclerView recycle;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button joinButton;

    private LobbyPresenter presenter;

    public void update(Observable obj, Object arg) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);

        presenter = new LobbyPresenter();

        presenter.addObserver(this);

        recycle = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        recycle.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(view.getContext());
        recycle.setLayoutManager(mLayoutManager);

        joinButton = (Button) view.findViewById(R.id.joinbutton);
        joinButton.setEnabled(false);


        // specify an adapter (see also next example)
        mAdapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        };
        recycle.setAdapter(mAdapter);

        return view;
    }

    //Calling this function from GameListFragment is an error
    public int createNewGame(String gameName){
        return -1;
    }

    public boolean joinGame(int gameID){
        return true;
    }

}

