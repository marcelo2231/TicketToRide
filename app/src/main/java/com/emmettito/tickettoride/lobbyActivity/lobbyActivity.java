package com.emmettito.tickettoride.lobbyActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.lobbyPresenter;

import java.util.Observable;
import java.util.Observer;

public class lobbyActivity extends FragmentActivity implements lobbyPresenter.lobbyViewInterface, Observer {
    private Button createGameButton;
    private Button joinGameButton;

    private lobbyPresenter presenter;

    public void update(Observable obj, Object arg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lobby);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        presenter = new lobbyPresenter();

        presenter.addObserver(this);

        createGameButton = (Button) findViewById(R.id.buttonCreate);
        createGameButton.setEnabled(true);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                displayNewGameField();
            }
        });

        joinGameButton = (Button) findViewById(R.id.buttonJoin);
        joinGameButton.setEnabled(true);
        joinGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                displayGamesList();
            }
        });
    }

    public void displayGamesList() {
        Fragment fragment = new gameListFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }

    public void displayNewGameField(){
        Fragment fragment = new newGameFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }

    public void createNewGame(){}

    public void joinGame(){}

}
