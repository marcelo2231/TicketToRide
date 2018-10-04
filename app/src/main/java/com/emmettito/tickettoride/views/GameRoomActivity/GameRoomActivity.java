package com.emmettito.tickettoride.views.GameRoomActivity;

import android.app.Activity;
import android.os.Bundle;

import com.emmettito.models.Player;
import com.emmettito.tickettoride.presenters.GameRoomPresenter;

import java.util.ArrayList;
import java.util.Observable;

/*import android.support.v7.app.AppCompatActivity;*/

public class GameRoomActivity extends Activity implements GameRoomPresenter.GameRoomView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game_room);
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
