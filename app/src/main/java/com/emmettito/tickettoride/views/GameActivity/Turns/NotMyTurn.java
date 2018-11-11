package com.emmettito.tickettoride.views.GameActivity.Turns;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;

public class NotMyTurn implements Turn {

    public NotMyTurn() {}

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
    }

    @Override
    public void drawFaceUpTrainCard(GameActivity context, TrainCard card) {
        // the user can not draw a card, context does not change
    }

    @Override
    public void drawFaceDownTrainCard(GameActivity context, TrainCard card) {
        // the user can not draw a card, context does not change
    }

    @Override
    public void drawDestCards(GameActivity context) {
        // the user can not draw destination cards, context does not change
    }
}
