package com.emmettito.tickettoride.views.GameActivity.Turns;

import android.widget.Button;

import com.emmettito.tickettoride.presenters.GamePresenter;


public interface Turn {
    void enterChat(GamePresenter context);
    void leaveGame(GamePresenter context);
    void viewDestCard(GamePresenter context);
    void viewCommands(GamePresenter context);
    void claimRoute(GamePresenter context, int routeID);
    void drawFaceUpTrainCard(GamePresenter context, Button button, int buttonIndex);
    void drawFaceDownTrainCard(GamePresenter context);
    void drawDestCards(GamePresenter context);
}
