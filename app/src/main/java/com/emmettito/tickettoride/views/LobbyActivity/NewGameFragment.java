package com.emmettito.tickettoride.views.LobbyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.LobbyPresenter;
import com.emmettito.tickettoride.views.GameRoomActivity.GameRoomActivity;

import java.util.Observable;
import java.util.Observer;

public class NewGameFragment extends Fragment implements LobbyPresenter.lobbyView, Observer {

    private EditText gameNameText;

    private Button createButton;

    private LobbyPresenter presenter;

    /**
     *
     * Replace following once login activity is completed
     *
     */

    private String authToken = "7463033991224999bbf92da98a2d491e";

    private String username = "username";


    /**
     *
     * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     *
     */

    public void update(Observable obj, Object arg) {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_game, container, false);

        presenter = new LobbyPresenter();

        presenter.addObserver(this);

        gameNameText = (EditText) view.findViewById(R.id.text);

        gameNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Not implemented
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Not implemented
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    createButton.setEnabled(false);
                }
                else {
                    createButton.setEnabled(true);
                }
            }
        });

        createButton = (Button) view.findViewById(R.id.createbutton);
        createButton.setEnabled(false);
        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String gameName = gameNameText.getText().toString();

                createNewGame(gameName, username, authToken);
            }
        });

        return view;
    }

    public void createNewGame(String gameName, String username, String authToken){
        GameLobbyResult result = presenter.createNewGame(gameName, username, authToken);

        authToken = result.getRenewedAuthToken();

        /***
         *
         * This next section will be replaced with actual code later
         *
         */

        /*if (gameName.equals("false")) {
            Toast toast = Toast.makeText(getContext(), "Error: name already taken", Toast.LENGTH_SHORT);
            toast.show();

            return;
        }

        String combined = "Name: " + gameName;

        Toast toast = Toast.makeText(getContext(), combined, Toast.LENGTH_SHORT);
        toast.show();*/

        /***
         *
         *
         * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         *
         */

        if (!result.getSuccess()) {
            Toast toast = Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_SHORT);
            toast.show();

            return;
        }

        Intent intent = new Intent(getActivity(), GameRoomActivity.class);

        intent.putExtra("gameName", gameName);
        intent.putExtra("username", username);
        intent.putExtra("authToken", authToken);

        startActivity(intent);
    }

    public void joinGame(String gameName, String username, String authToken){}

}
