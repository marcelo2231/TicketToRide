package com.emmettito.tickettoride.views.GameActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.emmettito.tickettoride.R;

public class GameActivity extends AppCompatActivity {
    private Button chatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toast.makeText(this, "Game Started!", Toast.LENGTH_SHORT).show();

        chatButton = (Button) findViewById(R.id.openChatButton);
        chatButton.setEnabled(true);
        chatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {}
}
