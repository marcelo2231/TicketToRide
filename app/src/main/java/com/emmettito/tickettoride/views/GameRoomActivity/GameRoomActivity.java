package com.emmettito.tickettoride.views.GameRoomActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.emmettito.models.Player;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.facades.ServerFacade;
import com.emmettito.tickettoride.presenters.GameRoomPresenter;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;

import java.util.ArrayList;
import java.util.Observable;

/*import android.support.v7.app.AppCompatActivity;*/

public class GameRoomActivity extends Activity implements GameRoomPresenter.GameRoomView {

    private RecyclerView recycle;
    private RecyclerView.Adapter mAdapter;

    private Button leaveGameButton;
    private Button startGameButton;
    //private GameRoomProxy proxy;
    private ServerFacade facade = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_room);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //proxy = new GameRoomProxy();
        facade = ServerFacade.getInstance();

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
        if (facade.startGame()) {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Error: Could not start the game.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void leaveGame() {
        if (facade.leaveGame()) {
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
