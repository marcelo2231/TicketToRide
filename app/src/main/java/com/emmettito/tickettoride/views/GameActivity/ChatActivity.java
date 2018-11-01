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
/**
 *
 * ChatActivity.java
 *
 * The ChatActivity is the View class (using Model-View-Presenter) corresponding to the Game Chat.
 * It contains two buttons, exitChat and sendMessage buttons. They will direct the user to either
 * back to the game activity or it will send a message to the server depending on which button was pressed.
 * This activity is created from within the GameActivity class.
 *
 * Domain:
 *      exitChatButton: Button, leads to the GameActivity
 *      sendMessageButton: Button, sends a chat message to the server
 *      recycle: RecyclerView, contains the list of chat messages
 *      mAdapter: Adapter, adapter for the recyclerView
 *      mLayoutManager: LayoutManager, layout manager for the recyclerView
 *      messageText: EditText, contains the message to be ent
 *      presenter: ChatPresenter, presenter for the Activity
 *      messages: String List, list of messages on chat
 *      chatString: String, all chat combined in one string
 *      clientInstance: Client, singleton with client data
 *      timerHandler: Handler, handler used by runnable
 *      timerRunnable: Runnable, adds a timer to the chat
 *
 * LobbyActivity extends FragmentActivity.
 *
 * @author  Marcelo Archiza Almeida
 * @since   2018
 *
 * @invariant recycle refers to an actual GUI recycler view
 * @invariant mAdapter refers to
 * @invariant mLayoutManager refers to
 * @invariant exitChatButton refers to an actual GUI button
 * @invariant sendMessageButton refers to an actual GUI button
 * @invariant messageText refers to an actual GUI edit text
 * @invariant presenter refers to the chat presenter
 * @invariant messages refers to the list of messages on chat
 * @invariant chatString refers to the chat string displayed on GUI
 * @invariant clientInstance refers to the instance of the client
 * @invariant timerHandler refers to the handler
 * @invariant timerRunnable refers to the runnable
 *
 */
public class ChatActivity extends FragmentActivity implements ChatPresenter.chatView {
    /** Class level Variables **/
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

        /**
         * run
         *
         * This method defines the behavior of the Runnable run functionality and is inherited and
         * overridden from the Runnable parent class. This implementation add a 500 post delay.
         *
         * @pre None
         *
         * @post handler delays for 500 delayMillis
         *
         *
         */
        @Override
        public void run() {
            mAdapter.notifyDataSetChanged();
            timerHandler.postDelayed(this, 500);
        }
    };

    /**
     * onCreate
     *
     * This method defines the behavior of the creation of the class and overridden
     * from the FragmentAtivity parent class. This implementation prepares the variables
     * so they can inflate the layout.
     *
     * @param savedInstanceState refers to the saved instance state
     *
     * @pre None
     *
     * @post all variables are linked to the layout
     * @post all click listeners are set
     *
     *
     */
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
            /**
             * onClick
             *
             * This method defines the behavior of the click and is inherited from the
             * OnClickListener parent class. This implementation finish the activity.
             *
             * @pre None
             *
             * @post chat activity is closed
             *
             */
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "You Can't Exit The Chat!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        sendMessageButton = (Button) findViewById(R.id.sendMessageButton);
        sendMessageButton.setEnabled(false);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick
             *
             * This method defines the behavior of the click and is inherited from the
             * OnClickListener parent class. This implementation send a message so the server
             * can add the message to the chat.
             *
             * @pre None
             *
             * @post message is sent to server
             *
             */
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "You Can't Send Messages!", Toast.LENGTH_SHORT).show();

                sendMessage(messageText.getText().toString());

                messageText.getText().clear();
            }
        });

        messageText = (EditText) findViewById(R.id.messageEditText);
        messageText.addTextChangedListener(new TextWatcher() {
            /**
             * beforeTextChanged
             *
             * This method defines the behavior before text is changed and is inherited
             * and overridden from the TextWatcher parent class. This was not implemented.
             *
             * @pre None
             *
             * @post None
             *
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Not implemented
            }

            /**
             * onTextChanged
             *
             * This method defines the behavior while text is changed and is inherited
             * and overridden from the TextWatcher parent class. This was not implemented.
             *
             * @pre None
             *
             * @post None
             *
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Not implemented
            }

            /**
             * afterTextChanged
             *
             * This method defines the behavior after text is changed and is inherited
             * and overridden from the TextWatcher parent class. This implementation enable
             * the send button depending on the message added to the EditText.
             *
             * @param s refers to the editable field
             *
             * @pre s is not null
             *
             * @post button is disabled or enabled depending on the text entered
             *
             */
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

    /**
     * update
     *
     * This method defines the behavior of the chat update. This implementation updates the chat
     * depending on the data that is not on the chat yet.
     *
     * @param arg refers to the ChatResult instance in JSON String format
     *
     * @pre arg is not null
     * @pre art is not empty
     *
     * @post chat was updated
     *
     */
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

    /**
     * onBackPressed
     *
     * This method defines the behavior of the Android back button and is inherited and overridden
     * from the FragmentActivity parent class. This implementation disables the back button.
     *
     * @pre None
     *
     * @post None
     *
     */
    @Override
    public void onBackPressed() {}

    /**
     * sendMessage
     *
     * This method defines the behavior of sending a message to the server to be added to the chat
     * stored on the server side. This implementation send a message using the presenter and display
     * a message if it is not successful.
     *
     * @param message refers to the message that the player will add to the chat
     *
     * @pre message is not null
     * @pre message is not empty
     *
     * @post toast message is displayed when not successful
     * @post message is added to chat when successful
     *
     */
    public void sendMessage(String message) {
        if (message == null || message.isEmpty()) { return; }
        ChatResult result = presenter.sendMessage(message);

        if (!result.getSuccess()) {
            Toast toast = Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
        //Toast toast = Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT);
        //toast.show();
    }
}
