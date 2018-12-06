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

    private static Client client;
    private String host;

    public ClientCommunicator() {
       client = Client.getInstance();
       host = "http://" + client.getIpAddress() + ":" + "8080";
    }

    private HttpURLConnection getConnection(URL url, String requestType, boolean doOutput) throws java.io.IOException {
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        http.setRequestMethod(requestType);

        http.setReadTimeout(150000);

        http.setConnectTimeout(150000);

        http.setDoOutput(doOutput);

        http.addRequestProperty("Authorization", client.getToken());

        http.connect();

        return http;
    }

    private String getResponse(HttpURLConnection http) throws java.io.IOException {
        InputStream respBody = http.getInputStream();

        String newAuthToken = http.getRequestProperty("RenewedAuthToken");

        if (newAuthToken != null) {
            if (!newAuthToken.equals("")) {
                client.setToken(newAuthToken);
            }
        }

        return readString(respBody);
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
        String requestType = strings[1];
        String requestData = strings[2];

        try {

           // System.out.println(urlString);

            URL url = new URL(urlString);

            boolean doOutput;

            if (requestType.equals("POST")) {
                doOutput = true;
            }
            else {
                doOutput = false;
            }

            HttpURLConnection http = getConnection(url, requestType, doOutput);

            if (doOutput) {

                OutputStream requestBody = http.getOutputStream();

                writeString(requestData, requestBody);

                requestBody.close();
            }

            return getResponse(http);

        } catch (IOException e) {
            //e.printStackTrace();
        }

        return "Error: Could not connect to the server.";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

}
