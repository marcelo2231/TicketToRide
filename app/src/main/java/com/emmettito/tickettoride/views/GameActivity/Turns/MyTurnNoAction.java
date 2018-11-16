package com.emmettito.tickettoride.views.GameActivity.Turns;

import android.widget.Button;
import android.widget.Toast;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;

public class MyTurnNoAction implements Turn {

    private Client data;

    public MyTurnNoAction(){
        data = Client.getInstance();
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
        if (data.getTempColorChoice() == null) {
            String error = "You need to select which color of card to use.";
            Toast.makeText(context.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            return;
        }

        TrainColor chosen_color = data.getTempColorChoice();

        if (context.canClaimRoute(routeID, chosen_color)) {
            context.claimRoute(routeID, chosen_color);
            data.resetTempColorChoice();
            context.setTurnState(new NotMyTurn());
            context.endTurn();
        }
    }

    @Override
    public void drawFaceUpTrainCard(GameActivity context, Button button, int buttonIndex) {
        TrainCard card = data.getGame().getTrainCardDeck().getFaceUpCards().get(buttonIndex);
        context.drawFaceUpTrainCard(button, buttonIndex);

        if (card.getColor() != TrainColor.Wild) {
            context.setTurnState(new MyTurnDrewCard());
        }
        else {
            context.setTurnState(new NotMyTurn());
            context.endTurn();
        }
    }

    @Override
    public void drawFaceDownTrainCard(GameActivity context) {
        if (context.drawFaceDownTrainCard()) {
            context.setTurnState(new MyTurnDrewCard());
        }
    }

    @Override
    public void drawDestCards(GameActivity context) {
        context.drawDestCard(false);
        context.setTurnState(new NotMyTurn());
        context.endTurn();
    }
}
