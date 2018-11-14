package com.emmettito.tickettoride.views.GameActivity.Turns;

import android.widget.Button;
import android.widget.Toast;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;

public class NotMyTurn implements Turn {

    private String error;

    public NotMyTurn() {
        error = "It is not your turn.";
    }

    @Override
    public void enterChat(GameActivity context) {
        context.enterChat();
    }

    @Override
    public void leaveGame(GameActivity context) {
        context.leaveGame();
    }

    @Override
    public void viewDestCard(GameActivity context) {
        context.viewDestCard();
    }

    @Override
    public void viewCommands(GameActivity context) {
        context.viewCommands();
    }

    @Override
    public void claimRoute(GameActivity context, int routeID) {
        // the user can not claim a route, context does not change
        Toast.makeText(context.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void drawFaceUpTrainCard(GameActivity context, Button button, int buttonIndex) {
        // the user can not draw a card, context does not change
        Toast.makeText(context.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void drawFaceDownTrainCard(GameActivity context) {
        // the user can not draw a card, context does not change
        Toast.makeText(context.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void drawDestCards(GameActivity context) {
        // the user can not draw destination cards, context does not change
        Toast.makeText(context.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }
}
