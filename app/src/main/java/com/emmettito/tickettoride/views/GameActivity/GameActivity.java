package com.emmettito.tickettoride.views.GameActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.emmettito.tickettoride.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
