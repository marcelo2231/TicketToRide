package com.emmettito.tickettorideserver.communication;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.Scanner;

public class Serializer {

    private Gson gson;

    public Serializer() {
        gson = new Gson();
    }

    public String serialize(Object obj) {
        return gson.toJson(obj);
    }

    public Object deserialize(InputStream str, Class myClass) throws Exception {
        Scanner in = new Scanner(str);
        StringBuilder sb = new StringBuilder();
        try {
            while (in.hasNextLine()) {
                sb.append(in.nextLine());
            }
            return gson.fromJson(sb.toString(), myClass);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Serializer: Invalid Json input, unable to parse it.");
        }
    }
}