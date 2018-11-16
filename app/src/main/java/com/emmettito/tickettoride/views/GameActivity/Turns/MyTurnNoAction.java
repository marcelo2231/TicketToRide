package com.emmettito.tickettoride.views.GameActivity.Turns;

import android.widget.Button;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.presenters.GamePresenter;


public class MyTurnNoAction implements Turn {

    private Client data;
    private boolean done;

    public MyTurnNoAction(){
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
        if (done) {
            return;
        }
        done = true;

        if (data.getTempColorChoice() == null) {
            String error = "You need to select which color of card to use.";
            context.displayToast(error);
            done = false;
            return;
        }

        TrainColor chosen_color = data.getTempColorChoice();

        if (context.canClaimRoute(routeID, chosen_color)) {
            context.claimRoute(routeID, chosen_color);
            data.resetTempColorChoice();
            context.setTurnState(new NotMyTurn());
            context.endTurn();
        } else {
            done = false;
        }
    }

    @Override
    public void drawFaceUpTrainCard(GamePresenter context, Button button, int buttonIndex) {
        if (done) {
            return;
        }
        done = true;

        TrainCard card = data.getGame().getTrainCardDeck().getFaceUpCards().get(buttonIndex);
        context.getGameActivity().drawFaceUpTrainCard(button, buttonIndex);

        if (card.getColor() != TrainColor.Wild) {
            context.setTurnState(new MyTurnDrewCard());
        }
        else {
            context.setTurnState(new NotMyTurn());
            context.endTurn();
        }
    }

    @Override
    public void drawFaceDownTrainCard(GamePresenter context) {
        if (done) {
            return;
        }
        done = true;

        context.getGameActivity().drawFaceDownTrainCard();
//        context.setTurnState(new MyTurnDrewCard());
    }

    @Override
    public void drawDestCards(GamePresenter context) {
        if (done) {
            return;
        }
        done = true;

        context.drawDestCard(false);
        context.setTurnState(new NotMyTurn());
        context.endTurn();
    }
}
