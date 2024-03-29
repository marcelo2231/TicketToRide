package com.emmettito.tickettoride.views.GameActivity.Turns;

import android.widget.Button;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.presenters.GamePresenter;

public class MyTurnDrewCard implements Turn {

    private Client data;

    private boolean done;

    private String error;

    public MyTurnDrewCard(){
        data = Client.getInstance();
        done = false;
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
        error = "You have already picked a train card. You must select another one.";
        context.displayToast(error);
    }

    @Override
    public void drawFaceUpTrainCard(GamePresenter context, Button button, int buttonIndex) {
        if (done) {
            return;
        }
        done = true;

        TrainCard card = data.getGame().getTrainCardDeck().getFaceUpCards().get(buttonIndex);

        if (card.getColor() != TrainColor.Wild) {
            context.getGameActivity().drawFaceUpTrainCard(button, buttonIndex);
            context.setTurnState(new NotMyTurn());
            context.getGameActivity().createDialog("Your Turn Is Over.");
            context.endTurn();
        }
        else {
            done = false;
            error = "You cannot pick a wild card.";
            context.displayToast(error);
        }
    }

    @Override
    public void drawFaceDownTrainCard(GamePresenter context) {
        if (done) {
            return;
        }
        done = true;

        if (context.getGameActivity().drawFaceDownTrainCard()) {
            context.setTurnState(new NotMyTurn());
            context.getGameActivity().createDialog("Your Turn Is Over.");
            context.endTurn();
        }
    }

    @Override
    public void drawDestCards(GamePresenter context) {
        String error = "You have already drawn a train card. You must select another one.";
        context.displayToast(error);
    }
}
