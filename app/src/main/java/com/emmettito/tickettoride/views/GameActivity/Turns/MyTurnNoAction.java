package com.emmettito.tickettoride.views.GameActivity.Turns;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;

public class MyTurnNoAction implements Turn {

    public MyTurnNoAction(){}

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

        if (context.canClaimRoute(routeID)) {
            context.claimRoute(routeID);
            context.setTurnState(new NotMyTurn());
        }
        else {
            // TODO: ALERT USER THEY CAN NOT CLAIM THE ROUTE
        }
    }

    @Override
    public void drawFaceUpTrainCard(GameActivity context, TrainCard card) {
        // if card is not wild
        if (card.getColor() != TrainColor.Wild) {
            // TODO: DO SOMETHING WITH CARD
            context.setTurnState(new MyTurnDrewCard());
        }
        else {
            // TODO: DO SOMETHING WITH CARD
            context.setTurnState(new NotMyTurn());
        }
    }

    @Override
    public void drawFaceDownTrainCard(GameActivity context, TrainCard card) {
        // TODO: DO SOMETHING WITH CARD
        context.setTurnState(new MyTurnDrewCard());
    }

    @Override
    public void drawDestCards(GameActivity context) {
        // TODO: DO SOMETHING WITH DESTINATION CARDS
        context.setTurnState(new NotMyTurn());
    }
}
