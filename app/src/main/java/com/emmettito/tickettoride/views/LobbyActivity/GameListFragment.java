package com.emmettito.tickettoride.views.LobbyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.models.Results.GetGamesResult;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.LobbyPresenter;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;
import com.emmettito.tickettoride.views.GameRoomActivity.GameRoomActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GameListFragment extends Fragment implements LobbyPresenter.lobbyView {

    private RecyclerView recycle;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button joinButton;

    private LobbyPresenter presenter;

    private List<String[]> games;
    private String gameString = "";

    private Client clientInstance = Client.getInstance();

    private boolean isRunning = false;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            if (!isRunning) {
                return;
            }
            mAdapter.notifyDataSetChanged();
            timerHandler.postDelayed(this, 500);
        }
    };

    public void update(Object arg) {
        String newListString = (String) arg;

        if (newListString.equals(gameString)) {
            return;
        }

        gameString = newListString;

        GetGamesResult result = new Gson().fromJson(newListString, GetGamesResult.class);

        List<Game> gamesList = result.getData();

        List<String[]> gamesListStrings = new ArrayList<>();

        for (Game item : gamesList) {
            String[] tempList = new String[3];

            tempList[0] = item.getGameName();
            tempList[1] = Integer.toString(item.getPlayers().size());

            if (item.isStarted()){
                tempList[2] = "GameStarted";

                ArrayList<Player> players = item.getPlayers();

                boolean playerInGame = false;

                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).getPlayerName().equals(clientInstance.getUser())) {
                        playerInGame = true;
                        break;
                    }
                }

                if (!playerInGame) {
                    continue;
                }
            }
            else if (tempList[1].equals("1") || tempList[1].equals("0")) {
                tempList[2] = "Waiting for players";
            }
            else if (tempList[1].equals("5")) {
                tempList[2] = "Game Room Full";
            }
            else {
                tempList[2] = "Ready";
            }

            gamesListStrings.add(tempList);
        }

        //if (gamesListStrings.size() > 0) {
                games.clear();
                games.addAll(gamesListStrings);
        //}
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
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
                        if (Integer.parseInt(games.get(i)[1]) < 5 || games.get(i)[2].equals("GameStarted")) {
                            joinGame(games.get(i)[0], clientInstance.getUser(),games.get(i)[2]);
                            finish();
                        }
                        else {
                            Toast.makeText(getContext(), "Game room is full. Please choose another game.", Toast.LENGTH_SHORT).show();
                        }
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

        mAdapter = new GameListAdapter(games, joinButton);
        recycle.setAdapter(mAdapter);

        timerHandler.postDelayed(timerRunnable, 500);
        isRunning = true;

        presenter = new LobbyPresenter(this);

        //presenter.addObserver(this);

        presenter.startPoller();

        return view;
    }

    public void finish() {
        super.onResume();
        /*if (presenter != null) {
            Client client = Client.getInstance();
            presenter.setGame(client.getGame());
        }*/
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        presenter.shutDownPoller();
    }

    public void createNewGame(String gameName, String username){}

    public void joinGame(String gameName, String username, String hasStarted) {
        presenter.shutDownPoller();
        isRunning = false;

        clientInstance.setGameName(gameName);

        if(hasStarted.equals("GameStarted")){
            Intent intent = new Intent(getActivity(), GameActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        //clientInstance.setGameName(gameName);
        GameLobbyResult result = presenter.joinGame(gameName, username);

        String token = result.getRenewedAuthToken();


        if (!result.getSuccess()) {
            Toast toast = Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_SHORT);
            toast.show();

            presenter.startPoller();

            return;
        }

        if (!token.equals("")) {
            clientInstance.setToken(token);
        }
        clientInstance.setGameName(gameName);

        Intent intent = new Intent(getActivity(), GameRoomActivity.class);

        startActivity(intent);
    }
}

