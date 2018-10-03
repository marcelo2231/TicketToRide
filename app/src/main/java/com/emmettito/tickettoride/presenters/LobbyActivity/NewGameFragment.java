package com.emmettito.tickettoride.presenters.LobbyActivity;

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

import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.LobbyPresenter;

import java.util.Observable;
import java.util.Observer;

public class NewGameFragment extends Fragment implements LobbyPresenter.lobbyView, Observer {

    private EditText gameNameText;

    private Button createButton;

    private LobbyPresenter presenter;

    public void update(Observable obj, Object arg) {

    }

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

                createNewGame(gameName);
            }
        });

        return view;
    }

    public void createNewGame(String gameName){
        int gameID;

        /***
         *
         * This next section will be replaced with actual code later
         *
         */


        if (gameName.equals("false")) {
            Toast toast = Toast.makeText(getContext(), "Error: name already taken", Toast.LENGTH_SHORT);
            toast.show();

            return;
        }
        else {
            gameID = 72834;
        }

        String combined = "Name: " + gameName + "\nID: " + gameID;

        Toast toast = Toast.makeText(getContext(), combined, Toast.LENGTH_SHORT);
        toast.show();

        /***
         *
         *
         * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         *
         */

        Intent intent = new Intent(getActivity(), DummyActivity.class);

        intent.putExtra("gameID", gameID);

        startActivity(intent);
    }

    public void joinGame(int gameID){}

}
