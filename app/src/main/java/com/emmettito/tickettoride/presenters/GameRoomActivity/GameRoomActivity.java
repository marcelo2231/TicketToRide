package com.emmettito.tickettoride.presenters.GameRoomActivity;

import android.app.Activity;
/*import android.support.v7.app.AppCompatActivity;*/
import android.os.Bundle;

import com.emmettito.models.Player;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.GameRoomPresenter;

import java.util.ArrayList;
import java.util.Observable;

public class GameRoomActivity extends Activity implements GameRoomPresenter.GameRoomView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_room);
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return null;
    }

    @Override
    public void startGame() {

    }

    @Override
    public void leaveGame() {

    }

    @Override
    public void cancelGame() {

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
