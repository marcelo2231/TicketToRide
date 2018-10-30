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

public class LoginActivity extends FragmentActivity implements LoginPresenter.LoginView, Observer {

    private Client client;

    private LoginPresenter presenter;

    private ServerFacade facade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_login);

        client = Client.getInstance();

        facade = ServerFacade.getInstance();

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

    @Override
    public void onBackPressed() {}

    private LoginRequest getLoginRequest() {
        LoginRequest request = new LoginRequest();

        EditText usernameSpace = (EditText) findViewById(R.id.usernameEditText);
        EditText passwordSpace = (EditText) findViewById(R.id.passwordEditText);

        request.setUsername(usernameSpace.getText().toString());
        request.setPassword(passwordSpace.getText().toString());

        return request;
    }

    private RegisterRequest getRegisterRequest() {
        RegisterRequest request = new RegisterRequest();

        EditText usernameSpace = (EditText) findViewById(R.id.usernameEditText);
        EditText passwordSpace = (EditText) findViewById(R.id.passwordEditText);

        request.setUsername(usernameSpace.getText().toString());
        request.setPassword(passwordSpace.getText().toString());

        return request;
    }

    @Override
    public void login() {
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

    @Override
    public void register() {
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

    private void switchToLobby() {
        Intent intent = new Intent(getApplicationContext(), LobbyActivity.class);
        startActivity(intent);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
