package com.emmettito.tickettoride.views.LoginActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emmettito.models.CommandModels.UserCommands.LoginRequest;
import com.emmettito.models.CommandModels.UserCommands.RegisterRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.communication.proxy.LoginProxy;
import com.emmettito.tickettoride.presenters.LoginPresenter;

import java.util.Observable;
import java.util.Observer;

public class LoginActivity extends FragmentActivity implements LoginPresenter.LoginView, Observer {

    private Client client;

    private LoginPresenter presenter;

    private LoginProxy proxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        client = Client.getInstance();
        proxy = new LoginProxy();

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

        request.setUsername("username");
        request.setPassword("password");

        return request;
    }

    @Override
    public void login() {
        LoginRequest request = getLoginRequest();
        Result result = proxy.login(request);
        Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void register() {
        RegisterRequest request = getRegisterRequest();
        Result result = proxy.register(request);

        Toast.makeText(this, request.getUsername(), Toast.LENGTH_SHORT).show();

        if (result != null) {
            Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "EMPTY", Toast.LENGTH_SHORT).show();
        }


    }

    private void switchToLobby() {

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
