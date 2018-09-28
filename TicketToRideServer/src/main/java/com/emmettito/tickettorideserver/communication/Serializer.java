package com.emmettito.tickettorideserver.communication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Serializer {

    private Gson gson;

    public Serializer() {
        gson = new Gson();
    }

    public String serialize(Object obj) {
        return gson.toJson(obj);
    }

    public Object deserialize(InputStream str) throws Exception {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(str);
        char[] buf = new char[1024];
        int len;
        try {
            while ((len = sr.read(buf)) > 0) {
                sb.append(buf, 0, len);
            }

            return gson.fromJson(sb.toString(), Object.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new Exception("Wrong Json input. Unable to parse it.");
        }
    }
}

//Other option to deserialize:
//
//    Scanner in = new Scanner(exchange.getRequestBody());
//
//    StringBuilder sb = new StringBuilder();
//
//        while (in.hasNextLine()) {
//        sb.append(in.nextLine());
//    }