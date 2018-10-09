package com.emmettito.tickettoride.views.GameRoomActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emmettito.models.Player;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.models.Results.GetPlayersResult;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.communication.proxy.GameRoomProxy;
import com.emmettito.tickettoride.presenters.GameRoomPresenter;

import java.util.ArrayList;
import java.util.Observable;

import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;
import com.emmettito.tickettoride.views.LobbyActivity.GameListAdapter;
import com.google.gson.Gson;

/*import android.support.v7.app.AppCompatActivity;*/

public class GameRoomActivity extends Activity implements GameRoomPresenter.GameRoomView {

    private Client client;

    private RecyclerView recycle;
    private RecyclerView.Adapter mAdapter;

    private Button leaveGameButton;
    private Button startGameButton;

    private GameRoomProxy proxy;
    private GameRoomPresenter presenter;

    private ArrayList<Player> players;

    private Handler timerHandler;
    private Runnable timerRunnable;

    private boolean polling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_room);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        client = Client.getInstance();

        TextView gameName = findViewById(R.id.gameNameLabel);
        gameName.setText(client.getGameName());

        proxy = new GameRoomProxy();
        presenter = new GameRoomPresenter();

        leaveGameButton = (Button) findViewById(R.id.leaveGameButton);
        leaveGameButton.setEnabled(true);
        leaveGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                leaveGame();
            }
        });

        startGameButton = (Button) findViewById(R.id.startGameButton);
        startGameButton.setEnabled(true);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGame();
            }
        });

        players = new ArrayList<>();

        recycle = (RecyclerView) findViewById(R.id.playerList);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new PlayerListAdapter(players);
        recycle.setHasFixedSize(true);
        recycle.setAdapter(mAdapter);

        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                    mAdapter.notifyDataSetChanged();
                    if (polling) {
                        timerHandler.postDelayed(this, 500);
                    }
                    Log.w("timerRunnable", "Should have updated display, size: " + players.size());
            }
        };

        startPoller();
    }

    public void startPoller() {
        polling = true;
        presenter.startPoller("http://10.0.2.2:8080/gamelobby/getPlayers", this);
        timerRunnable.run();
    }

    public void stopPoller() {
        polling = false;
        presenter.shutDownPoller();
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return null;
    }

    @Override
    public void startGame() {
        stopPoller();
        GameLobbyResult result = proxy.startGame();

        if (result.getSuccess()) {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
            startPoller();
        }
    }

    @Override
    public void leaveGame() {
        stopPoller();

        if (proxy.leaveGame()) {
            finish();
        }
        else {
            Toast.makeText(this, "An error occurred while trying to leave the game", Toast.LENGTH_SHORT).show();
            // Could restart polling...
        }
    }

    @Override
    public void cancelGame() {
        // NOT NEEDED FOR PHASE 1
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPoller();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o != null) {
            if (o.getClass() == String.class) {
                Log.w("GameRoomUpdated", "received: " + o.toString());
                GetPlayersResult results = new Gson().fromJson((String)o, GetPlayersResult.class);
                if(results.getDidGameStart()){
                    Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                    startActivity(intent);
                    finish();
                }

                players.clear();
                players.addAll(results.getData());

                Log.w("GameRoomUpdated", "should have updated " + players.size());
            }
        else {
                Log.w("GameRoomUpdated", o.getClass() + ": " + o.toString());
            }
        }
    }

    @Override
    public void onBackPressed() {}
}
