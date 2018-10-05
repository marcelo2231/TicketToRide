package com.emmettito.tickettoride.views.GameRoomActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emmettito.models.Player;
import com.emmettito.tickettoride.presenters.GameRoomPresenter;

import java.util.ArrayList;
import java.util.Observable;

import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;

/*import android.support.v7.app.AppCompatActivity;*/

public class GameRoomActivity extends Activity implements GameRoomPresenter.GameRoomView {

    //private Button logoutButton;
    private Button leaveGameButton;
    private Button startGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_room);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        /*
        logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setEnabled(true);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                displayNewGameField();
            }
        });
        */

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

        //INSERT LOGIC HERE

        //Might crash haven't tested it yet...
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void leaveGame() {
        finish();
    }

    @Override
    public void cancelGame() {

    }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
