package com.emmettito.tickettorideserver.communication.handlers;

import com.emmettito.models.CommandModels.GameLobbyCommandType;
import com.emmettito.models.CommandModels.GameLobbyCommands.*;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.gameLobby.*;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.gameLobby.RemoveGameCommand;
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
        Result result = new Result();
        InputStream input = httpExchange.getRequestBody();
        URI uri;
        String[] requestURI;
        String commandType;

/** TEST JSON STRING **/
/**
        String inputS = "{\n" +
                "  \"type\": \"CreateGame\",\n" +
                "  \"data\": " +
                    "{\n" +
                        "  \"createGameCommandModel\": {\n" +
                        "\"gameName\": \"NewGameMarcy\","+
                        "\"username\": \"marceloarchiza\""+
                        "\n}\n" +
                    "}" +
                "\n}";
        input = new ByteArrayInputStream(inputS.getBytes());
**/

        try {
            /** Get Path */
            uri = httpExchange.getRequestURI();
            requestURI = uri.getPath().split("/");

            if (requestURI.length < 3){
                List<GameLobbyCommandType> commandTypes = Arrays.asList(GameLobbyCommandType.values());
                throw new IOException("GameCommandType is invalid. Make sure to use one of the following commands: " + commandTypes);
            }else{
                commandType = requestURI[2];
            }

            switch(commandType){
                case "CreateGame":
                    result = new CreateGameCommand().execute(input);
                    break;
                case "QuitGame":
                    result = new QuitGameCommand().execute(input);
                    break;
                case "RemoveGame":
                    result = new RemoveGameCommand().execute(input);
                    break;
                case "JoinGame":
                    result = new JoinGameCommand().execute(input);
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
