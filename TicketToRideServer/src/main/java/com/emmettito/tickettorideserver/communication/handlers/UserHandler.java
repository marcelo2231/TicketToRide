package com.emmettito.tickettorideserver.communication.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static java.net.HttpURLConnection.HTTP_OK;

public class UserHandler implements HttpHandler {

    private Serializer serializer;

    public UserHandler() {
        serializer = new Serializer();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(HTTP_OK, 0);

        // De-serializes request
        Scanner in = new Scanner(exchange.getRequestBody());

        StringBuilder sb = new StringBuilder();

        while (in.hasNextLine()) {
            sb.append(in.nextLine());
            //sb.append("\n"); // not really needed idk
        }

        in.close();


        // INSERT ACTUAL CODE HERE

        String results = null; // RESULTS CAN BE A MODEL CLASS AND NOT A STRING

        // INSERT ACTUAL CODE HERE


        // Writes the JSON to the response body
        PrintWriter out = new PrintWriter(exchange.getResponseBody());

        String json = serializer.serialize(results);
        System.out.println(json);

        out.println(json);

        out.flush();
        out.close();
    }
}
