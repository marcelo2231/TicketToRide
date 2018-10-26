package com.emmettito.tickettoride.views.GameActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.emmettito.models.Game;
import com.emmettito.tickettoride.R;

public class GameActivity extends AppCompatActivity {
    private Game game;
    private Button chatButton;
    private Button trainCard1;
    private Button trainCard2;
    private Button trainCard3;
    private Button trainCard4;
    private Button trainCard5;
    private Button deckTrainCards;
    private Button deckDestinationCards;
    private Button viewDestinationCardsButton;
    private Button leaveGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game = new Game();
        Toast.makeText(this, "Game Started!", Toast.LENGTH_SHORT).show();

        chatButton = (Button) findViewById(R.id.openChatButton);
        chatButton.setEnabled(true);
        chatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);

                startActivity(intent);
            }
        });

        leaveGameButton = (Button) findViewById(R.id.leaveGameButton);
        leaveGameButton.setEnabled(true);
        leaveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //return to the join game screen
            }
        });

        deckTrainCards = (Button) findViewById(R.id.TrainCardsDeck);
        //disable all draw-card buttons unless it's the player's turn
        deckTrainCards.setEnabled(false);
        deckTrainCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Drawing train card", Toast.LENGTH_SHORT).show();
            }
        });

        trainCard1 = (Button) findViewById(R.id.trainCard1);
        trainCard1.setEnabled(false);
        trainCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Drawing face-up card 1", Toast.LENGTH_SHORT).show();
            }
        });

        trainCard2 = (Button) findViewById(R.id.trainCard2);
        trainCard2.setEnabled(false);
        trainCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Drawing face-up card 2", Toast.LENGTH_SHORT).show();
            }
        });

        trainCard3 = (Button) findViewById(R.id.trainCard3);
        trainCard3.setEnabled(false);
        trainCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Drawing face-up card 3", Toast.LENGTH_SHORT).show();
            }
        });

        trainCard4 = (Button) findViewById(R.id.trainCard4);
        trainCard4.setEnabled(false);
        trainCard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Drawing face-up card 4", Toast.LENGTH_SHORT).show();
            }
        });

        trainCard5 = (Button) findViewById(R.id.trainCard5);
        trainCard5.setEnabled(false);
        trainCard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Drawing face-up card 5", Toast.LENGTH_SHORT).show();
            }
        });

        deckDestinationCards = (Button) findViewById(R.id.deckDestinationCards);
        deckDestinationCards.setEnabled(false);
        deckDestinationCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Drawing 3 destination cards", Toast.LENGTH_SHORT).show();
            }
        });

        viewDestinationCardsButton = (Button) findViewById(R.id.desinationCardsButton);
        viewDestinationCardsButton.setEnabled(true);
        viewDestinationCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Open a view to see the player's destination cards", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
