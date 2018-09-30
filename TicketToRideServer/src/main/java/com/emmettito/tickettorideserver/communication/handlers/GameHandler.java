package com.emmettito.tickettorideserver.communication.handlers;

import com.emmettito.models.CommandModels.GameCommandData;
import com.emmettito.models.CommandModels.GameCommandType;
import com.emmettito.models.Result;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.game.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class GameHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        /** Variables */
        Serializer serializer = new Serializer();
        Result result = new Result();
        InputStream input = httpExchange.getRequestBody();

/**
         String inputS = "{\n" +
         "  \"type\": \"CompleteDestCard\",\n" +
         "  \"data\": " +
             "{\n" +
             "  \"PlayerID\": \"123546DSA56465ad\"\n" +
             "}" +
             "\n}";
         input = new ByteArrayInputStream(inputS.getBytes());
**/

        try {
            /** Command Data (Create a GameCommandData Object to store Information */
            GameCommandData cd = serializer.deserializeGame(input);

            if (cd.getType() == null){
                List<GameCommandType> commandTypes = Arrays.asList(GameCommandType.values());
                throw new IOException("GameCommandType is invalid. Make sure to use one of the following commands: " + commandTypes);
            }

            switch(cd.getType()){
                case CompleteDestCard:
                    result = new CompleteDestCardCommand().execute(cd.getData());
                    break;
                case EndGame:
                    result = new EndGameCommand().execute(cd.getData());
                    break;
                case StartGame:
                    result = new StartGameCommand().execute(cd.getData());
                    break;
                case DrawTrain:
                    result = new DrawTrainCommand().execute(cd.getData());
                    break;
                case ClaimRoute:
                    result = new ClaimRouteCommand().execute(cd.getData());
                    break;
                case DrawDestCard:
                    result = new DrawDestCardCommand().execute(cd.getData());
                    break;
                case GetScore:
                    result = new GetScoreCommand().execute(cd.getData());
                    break;
                case PlayerTurn:
                    result = new PlayerTurnCommand().execute(cd.getData());
                    break;
                default:
                    throw new Exception("Path is invalid. This URL Path does not have permissions to make those changes.");
            }
        }
        catch(Exception e){
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
