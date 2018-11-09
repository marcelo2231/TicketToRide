package com.emmettito.tickettoride.views.GameActivity.Turns;

public interface Turn {
    void enterChat();
    void leaveGame();
    void viewDestCard();
    //void viewCommands();

    void claimRoute();
    void drawFaceUpLocomotive();
    void drawFaceDownTrainCard();
    void drawFaceUpTrainCard();
    void drawDestCards();
}
