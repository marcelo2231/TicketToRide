package com.emmettito.tickettoride.presenters;

import java.util.Observable;

public class LobbyPresenter extends Observable {

    //private List<Observer> observers;

    public LobbyPresenter() {
        //observers = new ArrayList<>();
    }

    /*@Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }*/

    public interface lobbyView {
        void createNewGame(String gameName);

        void joinGame(String gameName);
    }
}
