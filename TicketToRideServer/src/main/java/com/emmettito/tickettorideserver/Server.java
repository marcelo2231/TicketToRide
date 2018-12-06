package com.emmettito.tickettorideserver;

import com.emmettito.tickettorideserver.communication.handlers.DefaultHandler;
import com.emmettito.tickettorideserver.communication.handlers.GameHandler;
import com.emmettito.tickettorideserver.communication.handlers.GameLobbyHandler;
import com.emmettito.tickettorideserver.communication.handlers.UserHandler;
import com.emmettito.tickettorideserver.database.AbstractDAOFactory;
import com.emmettito.tickettorideserver.database.Database;
import com.emmettito.tickettorideserver.database.FactoryProducer;
import com.emmettito.tickettorideserver.database.IGameDAO;
import com.emmettito.tickettorideserver.database.IUserDAO;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**Server class: used to serve the Ticket to Ride clients*/
public class Server {

    /** Variables **/
    private HttpServer server;
    private static int MAX_WAITING_CONNECTIONS = 100;

    /**
     * main: implements the web api commands
     * @param args accepts the following command-line arguments:
     * 1. Port number on which the server will accept client connections. This value is an integer
    in the range 1-65535. EX: 8080
     */
    public static void main(String[] args) throws Exception {
        if (!areArgumentsValid(args)) {
            return;
        }

        String[] args1 = new String[] {"8080", "2"}; // arbitrary args statement used for testing

        try {
            initializeDatabases(args);
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
            return;
        }

        Server server = new Server();

        server.startServer(Integer.parseInt(args1[0]));

        System.out.printf("arg1: %s arg2: %s", args[0], args[1]);

        if (args.length == 3) {
            System.out.println(" args3: " + args[2]);
        }
    }

    /**
     * startServer: starts a new Ticket to Ride Server
     * @param port the port number to start the server at
     */
    private void startServer(int port) {

        try {
            server = HttpServer.create(new InetSocketAddress(port), MAX_WAITING_CONNECTIONS);
            System.out.println("Server started on port: " + port);
        }
        catch (IOException e) {
            System.out.println("Error: failed to start server on port: " + port);
            e.printStackTrace();
            return;
        }

        server.setExecutor(null); // Using default "executor"

        /** Create ServerZero.Handlers */
        server.createContext("/", new DefaultHandler());
        server.createContext("/user", new UserHandler());
        server.createContext("/game", new GameHandler());
        server.createContext("/gamelobby", new GameLobbyHandler());

        /**  Start ServerZero */
        server.start();
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+");  //match a number with optional '-' and decimal.
    }

    private static boolean areArgumentsValid(String args[]) {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Usage: ./Server [database_type] [delta_num] (optional) -wipe");
            return false;
        }

        if (!isNumeric(args[1])) {
            System.out.println("Error: argument 2 must be an integer.");
            return false;
        }

        if (args[1].equals("-")) {
            System.out.println("Error: argument 2 must be >= 0.");
            return false;
        }

        if (args.length == 3 && !args[2].equals("-wipe")) {
            System.out.println("Error: invalid third paramater.");
            System.out.println("Usage: ./Server [database_type] [delta_num] (optional) -wipe");
            return false;
        }

        return true;
    }

    private static void initializeDatabases(String args[]) throws Exception {
        Database database = Database.getInstance();
        IUserDAO userDAO = database.getUserDAO();
        IGameDAO gameDAO = database.getGameDAO();

        AbstractDAOFactory factory = FactoryProducer.getFactory(args[0]);

        if (factory == null) {
            throw new Exception("Error: invalid database type. Exiting.");
        }

        IUserDAO newUserDAO = factory.getUserDAO();
        IGameDAO newGameDAO = factory.getGameDAO();

        if (userDAO != null && gameDAO != null && args.length == 2) {   //Data in database, clearing not specified
            if (newUserDAO.getClass() != userDAO.getClass()) {
                throw new Exception("Error: Data in database. Cannot change database types without overwriting data. Run with -wipe to clear database.");
            }
            else {
                return;
            }
        }

        database.setGameDAO(newGameDAO);
        database.setUserDAO(newUserDAO);
    }
}
