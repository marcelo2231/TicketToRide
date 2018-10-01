package com.emmettito.models.CommandModels;

import com.emmettito.models.CommandModels.GameCommands.*;

public class GameCommand {
    /** Variables **/
    ClaimRouteCommandModel claimRouteCommandModel;
    CompleteDestCardCommandModel completeDestCardCommandModel;
    DrawDestCardCommandModel drawDestCardCommandModel;
    DrawTrainCommandModel drawTrainCommandModel;
    EndGameCommandModel endGameCommandModel;
    GetScoreCommandModel getScoreCommandModel;
    PlayerTurnCommandModel playerTurnCommandModel;
    StartGameCommandModel startGameCommandModel;

    /** Setters **/
    public void setClaimRouteCommandModel(ClaimRouteCommandModel claimRouteCommandModel) {
        this.claimRouteCommandModel = claimRouteCommandModel;
    }

    public void setCompleteDestCardCommandModel(CompleteDestCardCommandModel completeDestCardCommandModel) {
        this.completeDestCardCommandModel = completeDestCardCommandModel;
    }

    public void setDrawDestCardCommandModel(DrawDestCardCommandModel drawDestCardCommandModel) {
        this.drawDestCardCommandModel = drawDestCardCommandModel;
    }

    public void setDrawTrainCommandModel(DrawTrainCommandModel drawTrainCommandModel) {
        this.drawTrainCommandModel = drawTrainCommandModel;
    }

    public void setEndGameCommandModel(EndGameCommandModel endGameCommandModel) {
        this.endGameCommandModel = endGameCommandModel;
    }

    public void setGetScoreCommandModel(GetScoreCommandModel getScoreCommandModel) {
        this.getScoreCommandModel = getScoreCommandModel;
    }

    public void setPlayerTurnCommandModel(PlayerTurnCommandModel playerTurnCommandModel) {
        this.playerTurnCommandModel = playerTurnCommandModel;
    }

    public void setStartGameCommandModel(StartGameCommandModel startGameCommandModel) {
        this.startGameCommandModel = startGameCommandModel;
    }
    /** Getters **/
    public ClaimRouteCommandModel getClaimRouteCommandModel() {
        return claimRouteCommandModel;
    }

    public CompleteDestCardCommandModel getCompleteDestCardCommandModel() {
        return completeDestCardCommandModel;
    }

    public DrawDestCardCommandModel getDrawDestCardCommandModel() {
        return drawDestCardCommandModel;
    }

    public DrawTrainCommandModel getDrawTrainCommandModel() {
        return drawTrainCommandModel;
    }

    public EndGameCommandModel getEndGameCommandModel() {
        return endGameCommandModel;
    }

    public GetScoreCommandModel getGetScoreCommandModel() {
        return getScoreCommandModel;
    }

    public PlayerTurnCommandModel getPlayerTurnCommandModel() {
        return playerTurnCommandModel;
    }

    public StartGameCommandModel getStartGameCommandModel() {
        return startGameCommandModel;
    }

}
