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
import android.view.Window;
import android.view.WindowManager;
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
    private boolean gameStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_game_room);

        client = Client.getInstance();

        final TextView gameName = findViewById(R.id.gameNameLabel);
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
        gameStart = false;

        recycle = (RecyclerView) findViewById(R.id.playerList);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new PlayerListAdapter(players);
        recycle.setHasFixedSize(true);
        recycle.setAdapter(mAdapter);

        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                if (gameStart) {
                    switchToGame();
                    Log.w("timerRunnable", "Should have started the game: " + gameStart);
                }
                mAdapter.notifyDataSetChanged();
                if (polling) {
                    timerHandler.postDelayed(this, 500);
                }
                Log.w("timerRunnable", "Game Start Status: " + gameStart);

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
    public void startGame() {

        GameLobbyResult result = proxy.startGame();

        if (result.getSuccess()) {
            switchToGame();
        }
        else {
            Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void switchToGame() {
        stopPoller();
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void leaveGame() {

        if (proxy.leaveGame()) {
            finish();
        }
        else {
            Toast.makeText(this, "An error occurred while trying to leave the game", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void update(Object o) {
        if (o != null) {
            if (o.getClass() == String.class) {
                Log.w("GameRoomUpdated", o.toString());
                GetPlayersResult results = new Gson().fromJson((String)o, GetPlayersResult.class);

                gameStart = results.getDidGameStart();

                players.clear();
                players.addAll(results.getData());
            }
        }
    }

    @Override
    public void onBackPressed() {}
}
