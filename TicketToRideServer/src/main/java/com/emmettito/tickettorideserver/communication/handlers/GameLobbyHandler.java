package com.emmettito.tickettorideserver.communication.handlers;

import com.emmettito.models.CommandModels.GameLobbyCommandType;
import com.emmettito.models.Results.GameLobbyResult;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.gameLobby.CreateGameCommand;
import com.emmettito.tickettorideserver.gameLobby.GetGamesCommand;
import com.emmettito.tickettorideserver.gameLobby.GetPlayersCommand;
import com.emmettito.tickettorideserver.gameLobby.JoinGameCommand;
import com.emmettito.tickettorideserver.gameLobby.StartGameCommand;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class GameLobbyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        /** Variables */
        Serializer serializer = new Serializer();
        Object result = new GameLobbyResult();
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
                List<GameLobbyCommandType> commandTypes = Arrays.asList(GameLobbyCommandType.values());
                throw new Exception("GameCommandType is invalid. Make sure to use one of the following commands: " + commandTypes);
            }else{
                commandType = requestURI[2];
            }

            switch(commandType.toLowerCase()){
                case "creategame":
                    result = new CreateGameCommand().execute(input, authToken);
                    break;
                case "joingame":
                    result = new JoinGameCommand().execute(input, authToken);
                    break;
                case "getgames":
                    result = new GetGamesCommand().execute(input, authToken);
                    break;
                case "getplayers":
                    result = new GetPlayersCommand().execute(input, authToken);
                    break;
                case "startgame":
                    result = new StartGameCommand().execute(input, authToken);
                    break;
                default:
                    throw new Exception("Path is invalid. This URL Path does not have permissions to make those changes.");
            }
        }
        catch(Exception e){
            result = new Result(false, "GameLobbyHandler: " + e.getMessage());
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
