package com.emmettito.tickettoride.views.LobbyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.emmettito.models.Game;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.models.Results.GetGamesResult;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.LobbyPresenter;
import com.emmettito.tickettoride.views.GameRoomActivity.GameRoomActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameListFragment extends Fragment implements Observer, LobbyPresenter.lobbyView {

    private RecyclerView recycle;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button joinButton;

    private LobbyPresenter presenter;

    private List<String[]> games;

    String authToken = "8101cd210696482e95bd056ba5519e2d";
    String username = "username";

    private Client clientInstance = Client.getInstance();

    public void update(Observable obj, Object arg) {
        String newListString = (String) arg;

        GetGamesResult result = new Gson().fromJson(newListString, GetGamesResult.class);

        List<Game> gamesList = result.getData();

        List<String[]> gamesListStrings = new ArrayList<>();

        for (Game item : gamesList) {
            String[] tempList = new String[3];

            tempList[0] = item.getGameName();
            tempList[1] = Integer.toString(item.getPlayers().size());

            if (tempList[1].equals("1")) {
                tempList[2] = "Waiting for players";
            }
            else if (tempList[1].equals("5")) {
                tempList[2] = "Game Room Full";
            }
            else {
                tempList[2] = "Ready";
            }
        }

        games = gamesListStrings;

       mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);

        games = new ArrayList<>();

        recycle = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        recycle.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(view.getContext());
        recycle.setLayoutManager(mLayoutManager);

        joinButton = (Button) view.findViewById(R.id.joinbutton);
        joinButton.setEnabled(false);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < recycle.getAdapter().getItemCount(); i++) {
                    if (mLayoutManager.findViewByPosition(i).isSelected()) {
                        joinGame(games.get(i)[0], username, authToken);
                    }
                }
            }
        });

        /*
         *
         * Remove following section when we have actual data
         *
         */

        /*String[] string1 = new String[4];
        String[] string2 = new String[4];
        String[] string3 = new String[4];

        string1[0] = "Game 1";
        string1[1] = "5";
        string1[2] = "Game Room Full";
        string1[3] = "76543";

        string2[0] = "Game 2";
        string2[1] = "1";
        string2[2] = "Waiting for players";
        string2[3] = "78373";


        string3[0] = "Game 3";
        string3[1] = "3";
        string3[2] = "Ready";
        string3[3] = "79823";


        games.add(string1);
        games.add(string2);
        games.add(string3);*/

        /*
         *
         * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         *
         */


        // specify an adapter (see also next example)
        mAdapter = new GameListAdapter(games, joinButton);
        recycle.setAdapter(mAdapter);

        //authToken = clientInstance.getToken();
        //username = clientInstance.getUser();


        presenter = new LobbyPresenter();

        presenter.addObserver(this);

        presenter.startPoller();

        return view;
    }

    public void createNewGame(String gameName, String username, String authToken){}

    public void joinGame(String gameName, String username, String authToken){
        GameLobbyResult result = presenter.joinGame(gameName, username, authToken);

        authToken = result.getRenewedAuthToken();
        clientInstance.setToken(authToken);
        clientInstance.setGameName(gameName);

        if (!result.getSuccess()) {
            Toast toast = Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_SHORT);
            toast.show();

            return;
        }

        Intent intent = new Intent(getActivity(), GameRoomActivity.class);

        startActivity(intent);
    }

}

