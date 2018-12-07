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

    public static void main(String[] args) throws Exception {
        Server server = new Server();

        Boolean wipe = server.checkArguments(args);

        String database_type = args[0];
        int delta_num = server.getDeltaNum(args[1]);

        System.out.printf("Database type: %s\nDelta number: %d\n", database_type, delta_num);

        try {
            server.initializeDatabases(database_type, wipe);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting...");
            return;
        }

        int port = 8080;
        server.startServer(port);
    }

    /**
     * startServer: starts a new Ticket to Ride Server
     * @param port the port number to start the server at
     */
    private void startServer(int port) {

        HttpServer server;

        try {
            int MAX_WAITING_CONNECTIONS = 100;
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

    private int getDeltaNum(String str) {
        // We don't want negative numbers and we need a whole number (integer)
        try {
            int i = Integer.parseInt(str);

            if (i > 0) {
                return i;
            }

            System.out.println("Error: delta_num must be > 0");
        }
        catch(Exception e) {
            System.out.println("Error: delta_num must be an integer");
        }

        printUsage();
        System.exit(-1);
        return -1;
    }

    private void printUsage() {
        System.out.println("Usage: Server database_type delta_num [-wipe]");
    }

    private boolean checkArguments(String args[]) {
        if (args.length < 2 || args.length > 3) {
            printUsage();
            System.exit(-1);
        }

        if (args.length == 3) {
            if (args[2].equals("-wipe")) {
                return true;
            }

            printUsage();
            System.out.println("Error: invalid third parameter");
            System.exit(-1);
        }

        return false;
    }

    private void initializeDatabases(String database_type, Boolean wipe) throws Exception {
        Database database = Database.getInstance();
        IUserDAO userDAO = database.getUserDAO();
        IGameDAO gameDAO = database.getGameDAO();

        AbstractDAOFactory factory = new FactoryProducer().getFactory(database_type);

        if (factory == null) {
            throw new Exception("Error: invalid database type");
        }

        IUserDAO newUserDAO = factory.getUserDAO();
        IGameDAO newGameDAO = factory.getGameDAO();

        if (userDAO != null && gameDAO != null && !wipe) {   // Data in database, clearing not specified //TODO: is this check for wiping the database valid/needed?
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
