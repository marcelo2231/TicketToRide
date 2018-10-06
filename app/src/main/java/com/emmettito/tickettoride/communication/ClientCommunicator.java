package com.emmettito.tickettoride.communication;

import android.os.AsyncTask;

import com.emmettito.tickettoride.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientCommunicator extends AsyncTask<String, Void, String> {
    // How to set the authorizationToken:
    // HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    // connection.addRequestProperty("Authorization", authToken);
    private static Client client;
    private static ClientCommunicator clientCommunicator = null;
    private String host;

    private ClientCommunicator() {
       client = Client.getInstance();
       host = "http://" + "10.0.2.2" + ":" + "8080";
    }

    public static ClientCommunicator getInstance()
    {
        if (clientCommunicator == null) {
            clientCommunicator = new ClientCommunicator();
        }

        return clientCommunicator;
    }

    /*public String post(String path, String requestData, String authToken) {
        return doInBackground(path, requestData, authToken, "POST");
    }*/

    /*public String get(String path, String authToken) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + path);

            HttpURLConnection http = getConnection(url, "GET", false, authToken);

            return getResponse(http);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error: Internal Server Error.";
    }*/

    private HttpURLConnection getConnection(URL url, String requestType, boolean doOutput, String authToken) throws java.io.IOException {
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        //System.out.println(requestType);

        http.setRequestMethod(requestType);

        http.setReadTimeout(150000);

        http.setConnectTimeout(150000);

        //http.setDoOutput(doOutput);

        http.addRequestProperty("Authorization", authToken);

        http.connect();

        //int responseCode = http.getResponseCode();

        //System.out.println(responseCode);

        //http.addRequestProperty("Accept", "application/json");

        //

        return http;
    }

    private String getResponse(HttpURLConnection http) throws java.io.IOException {
        //if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
        InputStream respBody = http.getInputStream();

        String newAuthToken = http.getRequestProperty("RenewedAuthToken");

        client.setToken(newAuthToken);

        return readString(respBody);
        //}
        //else {
        //    return "Error: Invalid request.";
        //}
    }

    private String readString(InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(stream);
        char[] buffer = new char[1024];
        int length;

        while ((length = reader.read(buffer)) > 0) {
            builder.append(buffer, 0, length);
        }

        return builder.toString();
    }

    private static void writeString(String str, OutputStream os) throws IOException {
        if (str.equals("")) {
            return;
        }
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    @Override
    protected String doInBackground(String... strings) {
        String urlString = strings[0];
        String authToken = strings[1];
        String requestType = strings[2];
        String requestData = strings[3];


        try {

            System.out.println(urlString);

            URL url = new URL(urlString);

            boolean doOutput;

            if (requestType.equals("POST")) {
                doOutput = true;
            }
            else {
                doOutput = false;
            }

            HttpURLConnection http = getConnection(url, requestType, doOutput, authToken);

            if (doOutput) {

                OutputStream requestBody = http.getOutputStream();

                writeString(requestData, requestBody);

                requestBody.close();
            }

            return getResponse(http);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error: Could not connect to the server.";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

}
