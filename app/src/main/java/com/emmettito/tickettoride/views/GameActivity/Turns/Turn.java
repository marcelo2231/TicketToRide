package com.emmettito.tickettoride.views.GameActivity.Turns;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;

public interface Turn {
    void enterChat(GameActivity context);
    void leaveGame(GameActivity context);
    void viewDestCard(GameActivity context);
    void viewCommands(GameActivity context);
    void claimRoute(GameActivity context, int routeID);
    void drawFaceUpTrainCard(GameActivity context, TrainCard card);
    void drawFaceDownTrainCard(GameActivity context, TrainCard card);
    void drawDestCards(GameActivity context);
}
