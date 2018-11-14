package com.emmettito.tickettoride.views.LoginActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emmettito.models.CommandModels.UserCommands.LoginRequest;
import com.emmettito.models.CommandModels.UserCommands.RegisterRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.facades.ServerFacade;
import com.emmettito.tickettoride.presenters.LoginPresenter;
import com.emmettito.tickettoride.views.LobbyActivity.LobbyActivity;

import java.util.Observable;
import java.util.Observer;

/**
 * This class is the first activity created upon starting the app.
 * The client and facade are singleton objects to control access to the server and limit the amount of clients that are created and used.
 * When a login or register attempt is successful, this class creates and calls a new LobbyActivity
 */
public class LoginActivity extends FragmentActivity implements LoginPresenter.LoginView{

    private Client client;

    private ServerFacade facade;

    /**
     * This method creates the first page of the app, which is the login screen.
     * It sets the orientation of the screen to landscape, initializes some classes that communicate with a server,
     * sets the layout of the app from the XML file, and creates buttons for logging in and registering. When the buttons
     * are pressed, call either the login or register method
     *
     * @param savedInstanceState the saved instance state of the app.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_login);

        client = Client.getInstance();

        facade = ServerFacade.getInstance("10.0.2.2", "8080");

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setEnabled(true);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setEnabled(true);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    /**
     * We are not implementing the back button for this activity
     */
    @Override
    public void onBackPressed() {}

    /**
     * This method fills and returns a LoginRequest object when the login button is pressed.
     *
     * @return the LoginRequest object containing the username and password that was entered by the user.
     */
    private LoginRequest getLoginRequest() {
        LoginRequest request = new LoginRequest();

        EditText usernameSpace = (EditText) findViewById(R.id.usernameEditText);
        EditText passwordSpace = (EditText) findViewById(R.id.passwordEditText);
        EditText ipSpace = (EditText) findViewById(R.id.ipEditText);

        request.setUsername(usernameSpace.getText().toString());
        request.setPassword(passwordSpace.getText().toString());
        client.setIpAddress(ipSpace.getText().toString());

        return request;
    }

    /**
     * This method creates and returns a RegisterRequest object when the register button is pressed.
     *
     * @return the RegisterRequest object that contains the username and password entered by the user
     */
    private RegisterRequest getRegisterRequest() {
        RegisterRequest request = new RegisterRequest();

        EditText usernameSpace = (EditText) findViewById(R.id.usernameEditText);
        EditText passwordSpace = (EditText) findViewById(R.id.passwordEditText);

        request.setUsername(usernameSpace.getText().toString());
        request.setPassword(passwordSpace.getText().toString());

        return request;
    }

    /**
     * This method is overridden from the Login Interface. When login is called, the request is created and sent to the server facade.
     * The facade returns a generic Result object from the server, which contains a success boolean with an authorization token or an error string.
     * An error string prompts an android toast with the error message.
     * When it returns successfully, call the switchToLobby method.
     */
    @Override
    public void login() {
        EditText ipSpace = (EditText) findViewById(R.id.ipEditText);
        client.setIpAddress(ipSpace.getText().toString());

        LoginRequest request = getLoginRequest();
        Result result = facade.login(request);

        if (result != null) {
            if (result.getSuccess()) {
                client.setUser(request.getUsername());
                client.setToken((String) result.getData());
                switchToLobby();
            }
            else {
                Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Error: Could not connect to the server.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method creates a RegisterRequest object, then sends it to the server facade.
     * The facade returns a result object, and if successful, calls the switchToLobby method.
     * When is fails, an android toast displays the error message.
     */
    @Override
    public void register() {
        EditText ipSpace = (EditText) findViewById(R.id.ipEditText);
        client.setIpAddress(ipSpace.getText().toString());

        RegisterRequest request = getRegisterRequest();
        Result result = facade.register(request);

        if (result != null) {
            if (result.getSuccess()) {
                client.setUser(request.getUsername());
                client.setToken((String) result.getData());
                switchToLobby();
            }
            else {
                Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Error: Could not connect to the server.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This creates and starts a new Intent for a LobbyActivity.
     * It is only called when login or register is successful.
     */
    private void switchToLobby() {
        Intent intent = new Intent(getApplicationContext(), LobbyActivity.class);
        startActivity(intent);
    }

}
