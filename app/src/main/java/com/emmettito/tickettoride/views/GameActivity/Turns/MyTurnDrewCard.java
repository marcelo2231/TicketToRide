package com.emmettito.tickettoride.views.GameActivity.Turns;

import android.widget.Button;
import android.widget.Toast;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;

public class MyTurnDrewCard implements Turn {

    private Client data;

    private String error;

    public MyTurnDrewCard(){
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
        error = "You must select another train card.";
        Toast.makeText(context.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void drawFaceUpTrainCard(GameActivity context, Button button, int buttonIndex) {
        TrainCard card = data.getGame().getTrainCardDeck().getFaceUpCards().get(buttonIndex);

        if (card.getColor() != TrainColor.Wild) {
            context.drawFaceUpTrainCard(button, buttonIndex);
            context.setTurnState(new NotMyTurn());
            context.endTurn();
        }
        else {
            error = "You cannot pick another wild card.";
            Toast.makeText(context.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void drawFaceDownTrainCard(GameActivity context) {
        context.drawFaceDownTrainCard();
        context.setTurnState(new NotMyTurn());
        context.endTurn();
    }

    @Override
    public void drawDestCards(GameActivity context) {
        String error = "You must select another train card.";
        Toast.makeText(context.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }
}
