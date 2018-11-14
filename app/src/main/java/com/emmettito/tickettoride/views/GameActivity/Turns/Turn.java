package com.emmettito.tickettoride.views.GameActivity.Turns;

import android.widget.Button;

import com.emmettito.tickettoride.views.GameActivity.GameActivity;

public interface Turn {
    void enterChat(GameActivity context);
    void leaveGame(GameActivity context);
    void viewDestCard(GameActivity context);
    void viewCommands(GameActivity context);
    void claimRoute(GameActivity context, int routeID);
    void drawFaceUpTrainCard(GameActivity context, Button button, int buttonIndex);
    void drawFaceDownTrainCard(GameActivity context);
    void drawDestCards(GameActivity context);
}
