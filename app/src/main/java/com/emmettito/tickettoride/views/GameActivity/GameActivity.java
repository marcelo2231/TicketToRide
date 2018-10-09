package com.emmettito.tickettoride.views.GameActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.emmettito.tickettoride.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toast.makeText(this, "Game Started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {}
}
