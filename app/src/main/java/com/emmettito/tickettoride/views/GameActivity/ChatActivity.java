package com.emmettito.tickettoride.views.GameActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emmettito.tickettoride.R;

public class ChatActivity extends AppCompatActivity {
    private Button exitChatButton;
    private Button sendMessageButton;
    private EditText messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toast.makeText(this, "Chat entered!", Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_chat);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        exitChatButton = (Button) findViewById(R.id.exitchatbutton);
        exitChatButton.setEnabled(true);
        exitChatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You Can't Exit The Chat!", Toast.LENGTH_SHORT).show();
            }
        });

        sendMessageButton = (Button) findViewById(R.id.sendMessageButton);
        sendMessageButton.setEnabled(false);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You Can't Send Messages!", Toast.LENGTH_SHORT).show();
            }
        });

        messageText = (EditText) findViewById(R.id.messageEditText);
        messageText.addTextChangedListener(new TextWatcher() {
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
                    sendMessageButton.setEnabled(false);
                }
                else {
                    sendMessageButton.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {}
}
