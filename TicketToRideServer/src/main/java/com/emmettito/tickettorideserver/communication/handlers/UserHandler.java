package com.emmettito.tickettorideserver.communication.handlers;

import com.emmettito.models.CommandModels.UserCommandData;
import com.emmettito.models.CommandModels.UserCommandType;
import com.emmettito.models.Result;
import com.emmettito.tickettorideserver.communication.Serializer;
import com.emmettito.tickettorideserver.user.*;
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

import static java.net.HttpURLConnection.HTTP_OK;

public class UserHandler implements HttpHandler {

    private Serializer serializer;

    public UserHandler() {
        serializer = new Serializer();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        /** Variables */
        Serializer serializer = new Serializer();
        Result result = new Result();
        InputStream input = httpExchange.getRequestBody();

/**
         String inputS = "{\n" +
         "  \"type\": \"Login\",\n" +
         "  \"data\": " +
                 "{\n" +
                     "  \"loginCommandModel\": {\n" +
                     "\"UserID\": \"123321DSADdfsa\""+
                     "\n}\n" +
                 "}" +
         "\n}";
         input = new ByteArrayInputStream(inputS.getBytes());
**/

        try {
            /** Command Data (Create a UserCommandData Object to store Information */
            UserCommandData cd = (UserCommandData) serializer.deserialize(input, UserCommandData.class);

            if (cd.getType() == null){
                List<UserCommandType> commandTypes = Arrays.asList(UserCommandType.values());
                throw new IOException("UserHandlerCommandType is invalid. Make sure to use one of the following commands: " + commandTypes);
            }

            switch(cd.getType()){
                case Login:
                    result = new LoginCommand().execute(cd.getData());
                    break;
                case Register:
                    result = new RegisterCommand().execute(cd.getData());
                    break;
                case Logout:
                    result = new LogoutCommand().execute(cd.getData());
                    break;
                default:
                    throw new Exception("Path is invalid. This URL Path does not have permissions to make those changes.");
            }
        }
        catch(Exception e){
            result = new Result(false, "UserHandler: " + e.getMessage());
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
