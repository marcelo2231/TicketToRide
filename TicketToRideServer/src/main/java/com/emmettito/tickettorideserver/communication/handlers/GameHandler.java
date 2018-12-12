package com.emmettito.tickettorideserver.communication.handlers;

import com.emmettito.models.CommandModels.GameCommandType;
import com.emmettito.models.CommandModels.GameCommands.PlayerTurnRequest;
import com.emmettito.models.CommandModels.GameCommands.SetGameRequest;
import com.emmettito.models.Results.Result;
import com.emmettito.models.Tuple;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.database.GameIMA;
import com.emmettito.tickettorideserver.game.ClaimRouteCommand;
import com.emmettito.tickettorideserver.game.CompleteDestCardCommand;
import com.emmettito.tickettorideserver.game.DiscardDestCardCommand;
import com.emmettito.tickettorideserver.game.DiscardTrainCardCommand;
import com.emmettito.tickettorideserver.game.DrawDestCardCommand;
import com.emmettito.tickettorideserver.game.DrawFaceUpTrainCommand;
import com.emmettito.tickettorideserver.game.DrawTrainCommand;
import com.emmettito.tickettorideserver.game.EndGameCommand;
import com.emmettito.tickettorideserver.game.GetCommandsCommand;
import com.emmettito.tickettorideserver.game.GetGameCommand;
import com.emmettito.tickettorideserver.game.GetScoreCommand;
import com.emmettito.tickettorideserver.game.IGameCommand;
import com.emmettito.tickettorideserver.game.PlayerTurnCommand;
import com.emmettito.tickettorideserver.game.QuitGameCommand;
import com.emmettito.tickettorideserver.game.RemoveGameCommand;
import com.emmettito.tickettorideserver.game.SetGameCommand;
import com.emmettito.tickettorideserver.game.chat.ChatCommand;
import com.emmettito.tickettorideserver.game.chat.GetChatCommand;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class GameHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        /** Variables */
        Serializer serializer = new Serializer();
        Object result = new Result();
        InputStream input = httpExchange.getRequestBody();
        URI uri;
        String[] requestURI;
        String commandType;
        Headers head = httpExchange.getRequestHeaders();
        String authToken = head.getFirst("Authorization");

        try {
            /** Get Path */
            uri = httpExchange.getRequestURI();
            requestURI = uri.getPath().split("/");

            if (requestURI.length < 3){
                List<GameCommandType> commandTypes = Arrays.asList(GameCommandType.values());
                throw new Exception("GameCommandType is invalid. Make sure to use one of the following commands: " + commandTypes);
            }else{
                commandType = requestURI[2];
            }

            Tuple commandTuple = null;

            String gameName = null;

            IGameCommand command = null;

            String requestString = null;
            try {
                requestString = IOUtils.toString((InputStream) input);
            } catch (Exception e) {
                e.printStackTrace();
            }

            input = IOUtils.toInputStream(requestString);

            InputStream newStream = null;

            switch(commandType.toLowerCase()){
                case "completedestcard":
                    result = new CompleteDestCardCommand().execute(input, authToken);
                    break;
                case "endgame":
                    result = new EndGameCommand().execute(input, authToken);
                    break;
                case "drawtraincard":
                    result = new DrawTrainCommand().execute(input, authToken);
                    break;
                case "drawfaceuptraincard":
                    result = new DrawFaceUpTrainCommand().execute(input, authToken);
                    break;
                case "claimroute":
                    result = new ClaimRouteCommand().execute(input, authToken);
                    break;
                case "discardtraincard":
                    result = new DiscardTrainCardCommand().execute(input, authToken);
                    break;
                case "discarddestcard":
                    result = new DiscardDestCardCommand().execute(input, authToken);
                    break;
                case "drawdestcard":
                    result = new DrawDestCardCommand().execute(input, authToken);
                    //System.out.println(input);
                    //command = new DrawDestCardCommand();
                    //newStream = IOUtils.toInputStream(requestString);
                    //DrawDestCardRequest request = (DrawDestCardRequest) new Serializer().deserialize((InputStream) newStream, DrawDestCardRequest.class);
                    //gameName = request.getGameName();
                    break;
                case "getscore":
                    result = new GetScoreCommand().execute(input, authToken);
                    break;
                case "playerturn":
                    //result = new PlayerTurnCommand().execute(input, authToken);
                    command = new PlayerTurnCommand();
                    newStream = IOUtils.toInputStream(requestString);
                    PlayerTurnRequest playerRequest = (PlayerTurnRequest) new Serializer().deserialize((InputStream) newStream, PlayerTurnRequest.class);
                    gameName = playerRequest.getGameName();
                    break;
                case "quitgame":
                    result = new QuitGameCommand().execute(input, authToken);
                    break;
                case "removegame":
                    result = new RemoveGameCommand().execute(input, authToken);
                    break;
                case "chat":
                    result = new ChatCommand().execute(input, authToken);
                    break;
                case "getchat":
                    result = new GetChatCommand().execute(input, authToken);
                    break;
                case "getgame":
                    result = new GetGameCommand().execute(input, authToken);
                    break;
                case "setgame":
                    //result = new SetGameCommand().execute(input, authToken);
                    command = new SetGameCommand();
                    newStream = IOUtils.toInputStream(requestString);
                    SetGameRequest gameRequest = (SetGameRequest) new Serializer().deserialize((InputStream) newStream, SetGameRequest.class);
                    gameName = gameRequest.getGame().getGameName();
                    break;
                case "getcommands":
                    result = new GetCommandsCommand().execute(input, authToken);
                    break;
                default:
                    throw new Exception("This URL path is invalid.");
            }

            if (command != null) {
                System.out.println(input.getClass());
                InputStream newInput = IOUtils.toInputStream(requestString);
                commandTuple = new Tuple(command, input);

                System.out.println("This is where I got");

                GameIMA ima = new GameIMA();
                ima.addDeltaCommand(gameName, authToken, commandTuple);
            }

        }
        catch(Exception e){
            e.printStackTrace();
            result = new Result(false, "GameHandler: " + e.getMessage());
        }
        finally {
            /** Return Result Message */
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            OutputStreamWriter send = new OutputStreamWriter(httpExchange.getResponseBody(), Charset.forName("UTF-8"));
            String output = serializer.serialize(result);
            send.write(output);
            send.close();
        }
    }
}
