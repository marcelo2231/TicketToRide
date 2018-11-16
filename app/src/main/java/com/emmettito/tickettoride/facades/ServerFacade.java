package com.emmettito.tickettoride.facades;


import com.emmettito.models.CommandModels.GameCommands.ChatRequest;
import com.emmettito.models.CommandModels.GameCommands.DiscardCardRequest;
import com.emmettito.models.CommandModels.GameCommands.DrawDestCardRequest;
import com.emmettito.models.CommandModels.GameCommands.DrawFaceUpTrainRequest;
import com.emmettito.models.CommandModels.GameCommands.DrawTrainRequest;
import com.emmettito.models.CommandModels.GameCommands.GetCommandsRequest;
import com.emmettito.models.CommandModels.GameCommands.GetGameRequest;
import com.emmettito.models.CommandModels.GameCommands.PlayerTurnRequest;
import com.emmettito.models.CommandModels.GameCommands.SetGameRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.CreateGameRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.GetPlayersRequest;
import com.emmettito.models.CommandModels.GameLobbyCommands.JoinGameRequest;
import com.emmettito.models.CommandModels.UserCommands.LoginRequest;
import com.emmettito.models.CommandModels.UserCommands.RegisterRequest;
import com.emmettito.models.Results.ChatResult;
import com.emmettito.models.Results.DrawDestCardResult;
import com.emmettito.models.Results.DrawTrainResult;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.models.Results.GetCommandsResult;
import com.emmettito.models.Results.GetGameResult;
import com.emmettito.models.Results.GetPlayersResult;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettoride.communication.proxy.GameLobbyProxy;
import com.emmettito.tickettoride.communication.proxy.GameProxy;
import com.emmettito.tickettoride.communication.proxy.GameRoomProxy;
import com.emmettito.tickettoride.communication.proxy.LoginProxy;
import com.emmettito.tickettoride.communication.proxy.ScoreProxy;

public class ServerFacade {
    private static ServerFacade instance = null;

    private GameLobbyProxy gameLobbyProxy;
    private GameProxy gameProxy;
    private GameRoomProxy gameRoomProxy;
    private LoginProxy loginProxy;
    private ScoreProxy scoreProxy;

    private String host = "";
    private String port = "";

    private ServerFacade(String host, String port) {
        gameLobbyProxy = new GameLobbyProxy(host, port);
        gameProxy = new GameProxy(host, port);
        loginProxy = new LoginProxy();
        gameRoomProxy = new GameRoomProxy();
        scoreProxy = new ScoreProxy();

        this.host = host;
        this.port = port;
    }

    public static ServerFacade getInstance(String host, String port) {
        if (instance == null) {
            instance = new ServerFacade(host, port);
        }
        return instance;
    }

    public GameLobbyResult createNewGame(CreateGameRequest request) {
        return gameLobbyProxy.createGame(request);
    }

    public GameLobbyResult joinGame(JoinGameRequest request) {
        return gameLobbyProxy.joinGame(request);
    }

    public ChatResult sendChatMessage(ChatRequest request) {
        gameProxy = new GameProxy(host, port);
        return gameProxy.sendChatMessage(request);
    }

    public GetPlayersResult getPlayers(GetPlayersRequest request) {
        gameProxy = new GameProxy(host, port);
        return gameProxy.getPlayers(request);
    }

    public GetCommandsResult getCommands(GetCommandsRequest request) {
        gameProxy = new GameProxy(host, port);
        return gameProxy.getCommands(request);
    }

    public GetGameResult getGame(GetGameRequest request) {
        gameProxy = new GameProxy(host, port);
        return gameProxy.getGame(request);
    }

    public DrawDestCardResult drawDestCard(DrawDestCardRequest request) {
        gameProxy = new GameProxy(host, port);
        return gameProxy.drawDestCard(request);
    }

    public Result discardDestCard(DiscardCardRequest request) {
        gameProxy = new GameProxy(host, port);
        return gameProxy.discardDestCard(request);
    }

    public Result login(LoginRequest request) {
        loginProxy = new LoginProxy();

        return loginProxy.login(request);
    }

    public Result register(RegisterRequest request) {
        loginProxy = new LoginProxy();

        return loginProxy.register(request);
    }

    public boolean startGame() {
        gameRoomProxy = new GameRoomProxy();

        return gameRoomProxy.startGame().getSuccess();
    }

    public boolean leaveGame() {
        gameRoomProxy = new GameRoomProxy();

        return gameRoomProxy.leaveGame();
    }

    public Result endTurn(PlayerTurnRequest request, String host, String port){
        gameProxy = new GameProxy(host, port);
        return gameProxy.endTurn(request);
    }

    public DrawTrainResult drawTrainCard(DrawTrainRequest request){
        gameProxy = new GameProxy(host, port);
        return gameProxy.drawTrainCard(request);
    }

    public DrawTrainResult drawFaceUpTrainCard(DrawFaceUpTrainRequest request) {
        gameProxy = new GameProxy(host, port);
        return gameProxy.drawFaceUpTrainCard(request);
    }

    public Result discardTrainCard(DiscardCardRequest request) {
        gameProxy = new GameProxy(host, port);
        return gameProxy.discardTrainCard(request);
    }

    public Result setGame(SetGameRequest request) {
        gameProxy = new GameProxy(host, port);
        return gameProxy.setGame(request);
    }
}

