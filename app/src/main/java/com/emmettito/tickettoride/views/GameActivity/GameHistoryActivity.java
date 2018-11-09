package com.emmettito.tickettoride.views.GameActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.emmettito.models.Results.GetCommandsResult;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;
import com.emmettito.tickettoride.presenters.GameHistoryPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GameHistoryActivity extends FragmentActivity implements GameHistoryPresenter.gameHistoryView {
    private RecyclerView recycle;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private GameHistoryPresenter presenter;
    private List<String[]> commands;
    private Client clientInstance = Client.getInstance();
    private String dataString = "";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_game_history);
        //Toast.makeText(this, "Chat entered!", Toast.LENGTH_SHORT).show();

        recycle = (RecyclerView) findViewById(R.id.my_recycler);

        recycle.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(mLayoutManager);

        commands = new ArrayList<>();

        mAdapter = new GameHistoryAdapter(commands, clientInstance.getUser());
        recycle.setAdapter(mAdapter);
        timerHandler.postDelayed(timerRunnable, 500);
        presenter = new GameHistoryPresenter(this);
        presenter.startPoller();
    }

    public void update(Object arg) {
        String newListString = (String) arg;

        if (newListString.equals(dataString)) {
            return;
        }

        dataString = newListString;

        GetCommandsResult result = new Gson().fromJson(newListString, GetCommandsResult.class);

        List<String[]> commandsList = result.getData();

        List<String[]> commandsStringsList = new ArrayList<>();

        if (commandsList == null) {
            return;
        }

        if (commandsList.size() > 0) {
            commands.clear();
            commands.addAll(commandsStringsList);
        }
    }

    @Override
    public void onBackPressed() {}
}
