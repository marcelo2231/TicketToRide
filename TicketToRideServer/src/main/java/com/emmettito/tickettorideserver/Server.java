package com.emmettito.tickettorideserver;

import com.emmettito.tickettorideserver.communication.handlers.GameHandler;
import com.emmettito.tickettorideserver.communication.handlers.GameLobbyHandler;
import com.emmettito.tickettorideserver.communication.handlers.UserHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**Server class: used to serve the Ticket to Ride clients*/
public class Server {

    /** Variables **/
    HttpServer server;
    private static int MAX_WAITING_CONNECTIONS = 100;

    /**
     * main: implements the web api commands
     * @param args accepts the following command-line arguments:
     * 1. Port number on which the server will accept client connections. This value is an integer
    in the range 1-65535. EX: 8080
     */
    public static void main(String[] args) throws Exception{

        args = new String[] {"8080", "2"}; // arbitrary args statement used for testing

        Server server = new Server();
        server.startServer(Integer.parseInt(args[0]));
    }

    /**
     * startServer: starts a new Ticket to Ride Server
     * @param port the port number to start the server at
     */
    public void startServer(int port) {

        System.out.println("Server started on port: " + port + "\n");

        try {
            server = HttpServer.create(new InetSocketAddress(port), MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            System.out.println("Error: failed to start server on port: " + port + "\n");
            e.printStackTrace();
            return;
        }

        server.setExecutor(null); // Using default "executor"

        /** Create ServerZero.Handlers */
        System.out.println("Creating contexts.");
        server.createContext("/user", new UserHandler());
        server.createContext("/game", new GameHandler());
        server.createContext("/game/lobby", new GameLobbyHandler());

        /**  Start ServerZero */
        System.out.println("Starting Server");
        server.start();
        System.out.println("Server started");
    }
}
