package com.emmettito.tickettoride.communication;

import android.os.AsyncTask;

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

    public ClientCommunicator() { }

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

        System.out.println(requestType);

        http.setRequestMethod(requestType);

        http.setConnectTimeout(1000);

        http.setDoOutput(doOutput);

        http.addRequestProperty("Authorization", authToken);

        //http.addRequestProperty("Accept", "application/json");

        http.connect();

        return http;
    }

    private String getResponse(HttpURLConnection http) throws java.io.IOException {
        //if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream respBody = http.getInputStream();

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
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    @Override
    protected String doInBackground(String... strings) {
        String urlString = strings[0];
        String requestData = strings[1];
        String authToken = strings[2];
        String requestType = strings[3];

        try {

            System.out.println(urlString);

            URL url = new URL(urlString);

            HttpURLConnection http = getConnection(url, requestType, true, authToken);

            OutputStream requestBody = http.getOutputStream();

            writeString(requestData, requestBody);

            requestBody.close();

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
