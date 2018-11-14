package com.emmettito.models.CommandModels.GameCommands;

import com.emmettito.models.Game;

public class SetGameRequest {
    /** Variables **/
    private Game game;

    /** Setters **/
    public void setGame(Game game) {
        this.game = game;
    }

    /** Getters **/
    public Game getGame() {
        return game;
    }
}
