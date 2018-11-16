package com.emmettito.tickettoride.views.GameActivity.Turns;

import android.widget.Button;

import com.emmettito.tickettoride.presenters.GamePresenter;

public class NotMyTurn implements Turn {

    private String error;

    public NotMyTurn() {
        error = "It is not your turn.";
    }

    @Override
    public void enterChat(GamePresenter context) {
        context.getGameActivity().enterChat();
    }

    @Override
    public void leaveGame(GamePresenter context) {
        context.getGameActivity().leaveGame();
    }

    @Override
    public void viewDestCard(GamePresenter context) {
        context.getGameActivity().viewDestCard();
    }

    @Override
    public void viewCommands(GamePresenter context) {
        context.getGameActivity().viewCommands();
    }

    @Override
    public void claimRoute(GamePresenter context, int routeID) {
        // the user can not claim a route, context does not change
        context.displayToast(error);
    }

    @Override
    public void drawFaceUpTrainCard(GamePresenter context, Button button, int buttonIndex) {
        // the user can not draw a card, context does not change
        context.displayToast(error);
    }

    @Override
    public void drawFaceDownTrainCard(GamePresenter context) {
        // the user can not draw a card, context does not change
        context.displayToast(error);
    }

    @Override
    public void drawDestCards(GamePresenter context) {
        // the user can not draw destination cards, context does not change
        context.displayToast(error);
    }
}
