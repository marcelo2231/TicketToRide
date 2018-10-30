package com.emmettito.tickettoride.views.LobbyActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.emmettito.models.Results.Result;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.LoginPresenter;
import com.emmettito.tickettoride.views.LoginActivity.LoginActivity;

public class LobbyActivity extends FragmentActivity {
    private Button createGameButton;
    private Button joinGameButton;
    private Button logoutButton;
    private LoginPresenter userPresenter;
    private Client clientInstance = Client.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_lobby);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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

        logoutButton = (Button) findViewById(R.id.buttonLogout);
        logoutButton.setEnabled(true);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logout(clientInstance.getUser());
            }
        });

        userPresenter = new LoginPresenter();
    }


    @Override
    public void onBackPressed() {}

    public void displayGamesList() {
        Fragment fragment = new GameListFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }

    public void displayNewGameField(){
        Fragment fragment = new NewGameFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }

    public void logout(String username){
        Result result = userPresenter.logout(username);

        if (!result.getSuccess()) {
            Toast toast = Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);

        finish();
    }
}
