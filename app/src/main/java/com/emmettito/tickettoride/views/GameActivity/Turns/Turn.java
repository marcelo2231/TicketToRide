package com.emmettito.tickettoride.views.GameActivity.Turns;

import com.emmettito.tickettoride.views.GameActivity.GameActivity;

public interface Turn {
    void enterChat(GameActivity context);
    void leaveGame(GameActivity context);
    void viewDestCard(GameActivity context);
    void viewCommands(GameActivity context);
    void claimRoute(GameActivity context);
    void drawFaceUpLocomotive(GameActivity context);
    void drawFaceDownTrainCard(GameActivity context);
    void drawFaceUpTrainCard(GameActivity context);
    void drawDestCards(GameActivity context);
}
