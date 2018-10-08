package com.emmettito.tickettoride.views.GameRoomActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.emmettito.models.Player;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.communication.proxy.GameRoomProxy;
import com.emmettito.tickettoride.presenters.GameRoomPresenter;

import java.util.ArrayList;
import java.util.Observable;

import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;

/*import android.support.v7.app.AppCompatActivity;*/

public class GameRoomActivity extends Activity implements GameRoomPresenter.GameRoomView {

    private Button leaveGameButton;
    private Button startGameButton;
    private GameRoomProxy proxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_room);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        proxy = new GameRoomProxy();

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
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return null;
    }

    @Override
    public void startGame() {
        GameLobbyResult result = proxy.startGame();
        if (result.getSuccess()) {
            Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void leaveGame() {
        if (proxy.leaveGame()) {
            finish();
        }
    }

    @Override
    public void cancelGame() {
        // NOT NEEDED FOR PHASE 1
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
