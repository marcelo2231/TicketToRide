package com.emmettito.tickettoride.views.GameActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emmettito.models.Results.ChatResult;
import com.emmettito.models.Tuple;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.ChatPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends FragmentActivity implements ChatPresenter.chatView {
    private RecyclerView recycle;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button exitChatButton;
    private Button sendMessageButton;
    private EditText messageText;

    private ChatPresenter presenter;

    private List<String[]> messages;
    private String chatString = "";

    private Client clientInstance = Client.getInstance();

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            mAdapter.notifyDataSetChanged();
            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_chat);
        //Toast.makeText(this, "Chat entered!", Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_chat);

        recycle = (RecyclerView) findViewById(R.id.my_recycler_view);

        recycle.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(mLayoutManager);

        messages = new ArrayList<>();

        exitChatButton = (Button) findViewById(R.id.exitchatbutton);
        exitChatButton.setEnabled(true);
        exitChatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "You Can't Exit The Chat!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        sendMessageButton = (Button) findViewById(R.id.sendMessageButton);
        sendMessageButton.setEnabled(false);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "You Can't Send Messages!", Toast.LENGTH_SHORT).show();

                sendMessage(messageText.getText().toString());

                messageText.getText().clear();
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

        mAdapter = new ChatAdapter(messages, clientInstance.getUser());
        recycle.setAdapter(mAdapter);

        timerHandler.postDelayed(timerRunnable, 500);

        presenter = new ChatPresenter(this);
        presenter.startPoller();
    }

    public void update(Object arg) {
        String newListString = (String) arg;

        if (newListString.equals(chatString)) {
            return;
        }

        chatString = newListString;

        ChatResult result = new Gson().fromJson(newListString, ChatResult.class);

        List<Tuple> chatList = result.getData();

        List<String[]> chatStringsList = new ArrayList<>();

        if (chatList == null) {
            return;
        }

        for (Tuple item : chatList) {
            String[] tempList = new String[2];

            tempList[0] = (String) item.getX();
            tempList[1] = (String) item.getY();

            chatStringsList.add(tempList);
        }

        if (chatStringsList.size() > 0) {
            messages.clear();
            messages.addAll(chatStringsList);
        }
    }

    @Override
    public void onBackPressed() {}

    public void sendMessage(String message) {
        ChatResult result = presenter.sendMessage(message);

        if (!result.getSuccess()) {
            Toast toast = Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }

        //Toast toast = Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT);
        //toast.show();
    }
}
