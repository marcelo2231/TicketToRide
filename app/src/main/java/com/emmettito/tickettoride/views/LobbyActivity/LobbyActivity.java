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

/**
 *
 * LobbyActivity.java
 *
 * The LobbyActivity is the View class (using Model-View-Presenter) corresponding to the Game Lobby.
 * It contains two buttons, createGameButton and joinGame button. It will direct the user to either
 * the CreateGameFragment or the JoinGameFragment depending on which button was pressed. This activity
 * is created from within the LoginActivity class.
 *
 * Domain:
 *      createGameButton: Button, leads to CreateGameFragment
 *      joinGameButton: Button, leads to JoinGameFragment
 *      logoutButton: Button, closes activity and leads to LoginActivity
 *      userPresenter: LoginPresenter instance, communicates with LobbyActivity and the model classes
 *      clientInstance: Client, a singleton model that stores information that is constant for the
 *                      client application as a whole.
 *
 * LobbyActivity extends FragmentActivity.
 *
 * @author  John Paul Andersen
 * @since   2018
 *
 * @invariant createGameButton refers to an actual GUI button
 * @invariant joinGameButton refers to an actual GUI button
 * @invariant logoutButton refers to an actual GUI button
 * @invariant LoginPresenter is a defined class
 * @invariant Client is a defined class
 *
 */
public class LobbyActivity extends FragmentActivity {
    private Button createGameButton;
    private Button joinGameButton;
    private Button logoutButton;
    private LoginPresenter userPresenter;
    private Client clientInstance = Client.getInstance();

    /**
     * onCreate
     *
     * This method sets up the current application state, including variables and the GUI.
     * It is inherited and overridden from the FragmentActivity parent class.
     *
     * @param savedInstanceState The saved state passed in by the calling activity.
     *
     * @pre None
     *
     * @post app in landscape mode
     * @post app displaying LobbyActivity layout
     * @post createGameButton not null
     * @post createGameButton pointing to correct Button
     * @post createGameButton enabled and clickable
     * @post joinGameButton not null
     * @post joinGameButton pointing to correct Button
     * @post joinGameButton enabled and clickable
     * @post logoutButton not null
     * @post logoutGameButton pointing to correct Button
     * @post logoutGameButton enabled and clickable
     * @post userPresenter not null
     *
     */
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

    /**
     * onBackPressed
     *
     * This method defines the behavior of the Android back button and is inherited and overridden
     * from the FragmentActivity parent class. This implementation disables the back button.
     *
     * @pre None
     *
     * @post None
     *
     *
     */
    @Override
    public void onBackPressed() {}

    /**
     * displayGamesList
     *
     * This method creates and starts a new GameListFragment and displays it on the screen.
     *
     * @pre LobbyActivity is the current active activity
     * @pre GameListFragment is a valid defined Android Fragment
     *
     * @post GameListFragment displayed on top of the LobbyActivity
     *
     */
    public void displayGamesList() {
        Fragment fragment = new GameListFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }

    /**
     * displayNewGameField
     *
     * This method creates and starts a new NewGameFragment and displays it on the screen.
     *
     * @pre LobbyActivity is the current active activity
     * @pre NewGameFragment is a valid defined Android Fragment
     *
     * @pre NewGameFragment displayed on top of LobbyActivity
     *
     */
    public void displayNewGameField(){
        Fragment fragment = new NewGameFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }

    /**
     * logout
     *
     * This method calls on the userPresenter to log out the current player. If successful, it exits
     * the activity and restarts the LoginActivity.
     *
     * @param username The String username of the player associated with this client instance.
     *
     * @pre username is not null
     * @pre username is a valid String equal to the current user's username
     * @pre userPresenter is not null
     * @pre userPresenter is able perform a logout function
     * @pre LoginActivity is a valid defined Android Activity
     *
     * @post LobbyActivity has been destroyed
     * @post LoginActivity is the current activity
     *
     */
    public void logout(String username){
        Result result = userPresenter.logout(username);

        if (!result.getSuccess()) {
            Toast toast = Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }

        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);

        finish();
    }
}
