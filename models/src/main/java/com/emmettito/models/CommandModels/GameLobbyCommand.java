package com.emmettito.models.CommandModels;

import com.emmettito.models.CommandModels.GameLobbyCommands.*;

public class GameLobbyCommand {
    /** Variables **/
    CreateGameCommandModel createGameCommandModel;
    JoinGameCommandModel joinGameCommandModel;
    QuitGameCommandModel quitGameCommandModel;
    RemoveGameCommandModel removeGameCommandModel;


    /** Setters **/
    public void setCreateGameCommandModel(CreateGameCommandModel createGameCommandModel) {
        this.createGameCommandModel = createGameCommandModel;
    }

    public void setJoinGameCommandModel(JoinGameCommandModel joinGameCommandModel) {
        this.joinGameCommandModel = joinGameCommandModel;
    }

    public void setQuitGameCommandModel(QuitGameCommandModel quitGameCommandModel) {
        this.quitGameCommandModel = quitGameCommandModel;
    }

    public void setRemoveGameCommandModel(RemoveGameCommandModel removeGameCommandModel) {
        this.removeGameCommandModel = removeGameCommandModel;
    }

    /** Getters **/
    public CreateGameCommandModel getCreateGameCommandModel() {
        return createGameCommandModel;
    }

    public JoinGameCommandModel getJoinGameCommandModel() {
        return joinGameCommandModel;
    }

    public QuitGameCommandModel getQuitGameCommandModel() {
        return quitGameCommandModel;
    }

    public RemoveGameCommandModel getRemoveGameCommandModel() {
        return removeGameCommandModel;
    }
}
