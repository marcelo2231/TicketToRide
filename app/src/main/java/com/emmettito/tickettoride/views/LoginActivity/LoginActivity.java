package com.emmettito.tickettoride.views.LoginActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emmettito.models.CommandModels.UserCommands.LoginRequest;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.LoginPresenter;

import java.util.Observable;
import java.util.Observer;

public class LoginActivity extends FragmentActivity implements LoginPresenter.LoginView, Observer {
    private LoginRequest request = new LoginRequest();

    private Button registerButton;
    private Button loginButton;
    private TextView usernameTextView;
    private TextView passwordTextView;
    private EditText usernameSpace;
    private EditText passwordSpace;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setEnabled(true);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setEnabled(true);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        passwordTextView = (TextView) findViewById(R.id.passwordTextView);

        usernameSpace = (EditText) findViewById(R.id.usernameEditText);
        usernameSpace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                request.setUsername(usernameSpace.getText().toString());
            }
        });

        passwordSpace = (EditText) findViewById(R.id.passwordEditText);
        passwordSpace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                request.setPassword(passwordSpace.getText().toString());
            }
        });
    }

    @Override
    public void login() {

    }

    @Override
    public void register() {

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
