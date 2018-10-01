package com.emmettito.tickettoride.lobbyActivity;

import java.util.Observable;

public class lobbyPresenter extends Observable {

    public interface lobbyViewInterface {
        void displayGamesList();

        void displayNewGameField();

        void createNewGame();

        void joinGame();
    }
}
