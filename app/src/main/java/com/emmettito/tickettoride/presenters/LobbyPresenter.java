package com.emmettito.tickettoride.presenters;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class LobbyPresenter extends Observable {

    private List<lobbyViewInterface> observers;

    public LobbyPresenter() {
        observers = new ArrayList<>();
    }

    public interface lobbyViewInterface {
        void displayGamesList();

        void displayNewGameField();

        void createNewGame();

        void joinGame();
    }
}
