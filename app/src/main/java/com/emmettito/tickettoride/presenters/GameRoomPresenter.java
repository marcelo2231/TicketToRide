package com.emmettito.tickettoride.presenters;

import com.emmettito.models.Player;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class GameRoomPresenter extends Observable {

    public interface GameRoomView extends Observer{

        /*public int getPlayers();*/

        ArrayList<Player> getPlayers();

        void startGame();

        void leaveGame();

        void cancelGame();
    }
}