package com.emmettito.tickettorideserver.communication.handlers;

import com.emmettito.models.CommandModels.GameLobbyCommandType;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.gameLobby.CreateGameCommand;
import com.emmettito.tickettorideserver.gameLobby.JoinGameCommand;
import com.emmettito.tickettorideserver.gameLobby.QuitGameCommand;
import com.emmettito.models.CommandModels.GameLobbyCommandData;
import com.emmettito.models.Results.Result;
import com.emmettito.tickettorideserver.gameLobby.RemoveGameCommand;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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
            /** Command Data (Create a GameLobbyCommandData Object to store Information */
            GameLobbyCommandData cd = (GameLobbyCommandData)serializer.deserialize(input, GameLobbyCommandData.class);

            if (cd.getType() == null){
                List<GameLobbyCommandType> commandTypes = Arrays.asList(GameLobbyCommandType.values());
                throw new IOException("GameLobbyCommandType is invalid. Make sure to use one of the following commands: " + commandTypes);
            }

            switch(cd.getType()){
                case CreateGame:
                    result = new CreateGameCommand().execute(cd.getData());
                    break;
                case QuitGame:
                    result = new QuitGameCommand().execute(cd.getData());
                    break;
                case RemoveGame:
                    result = new RemoveGameCommand().execute(cd.getData());
                    break;
                case JoinGame:
                    result = new JoinGameCommand().execute(cd.getData());
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
