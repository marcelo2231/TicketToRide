package com.emmettito.tickettoride.views.GameActivity.Turns;

import android.widget.Button;
import android.widget.Toast;

import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainColor;
import com.emmettito.models.City;
import com.emmettito.models.Route;
import com.emmettito.models.Tuple;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.views.GameActivity.GameActivity;

import java.util.HashMap;
import java.util.List;

public class MyTurnNoAction implements Turn {

    private Client data;

    private String error;

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
        if (context.canClaimRoute(routeID)) {
            context.claimRoute(routeID);
            context.setTurnState(new NotMyTurn());
        }
        else {
            Tuple route = data.getAllRoutes().get(routeID).getCities();
            City city_x = data.getAllCities().get((int)route.getX());
            City city_y = data.getAllCities().get((int)route.getY());
            error = "You cannot claim the route from " + city_x.getName() + " to " + city_y.getName();
            Toast.makeText(context.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
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
        }
    }

    @Override
    public void drawFaceDownTrainCard(GameActivity context) {
        context.drawFaceDownTrainCard();
        context.setTurnState(new MyTurnDrewCard());
    }

    @Override
    public void drawDestCards(GameActivity context) {
        context.drawDestCard(false);
        context.setTurnState(new NotMyTurn());
    }
}
