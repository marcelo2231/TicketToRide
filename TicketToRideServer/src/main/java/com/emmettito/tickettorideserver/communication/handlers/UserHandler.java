package com.emmettito.tickettorideserver.communication.handlers;

import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.model.CommandData;
import com.emmettito.tickettorideserver.model.CommandType;
import com.emmettito.tickettorideserver.model.Result;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Scanner;

import static java.net.HttpURLConnection.HTTP_OK;

public class UserHandler implements HttpHandler {

    private Serializer serializer;

    public UserHandler() {
        serializer = new Serializer();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(HTTP_OK, 0);

        /** Variables */
        Serializer serializer = new Serializer();
        Result result = new Result();
        InputStream input = httpExchange.getRequestBody();

        // De-serializes request
        try {
            CommandData cd = (CommandData) serializer.deserialize(input);
            switch (cd.getType()) {
                case CreateGame:
                    //result = new CreateGameCommand().execute();
                    break;
                default:
                    throw new Exception("CommandType is invalid. Make sure to use one of the following commands: " + CommandType.values());
            }
        }
        catch(Exception e){
            result = new Result(false, e.getMessage());
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
