package com.emmettito.tickettorideserver.communication;

import com.google.gson.Gson;

public class Serializer {

    private Gson gson;

    public Serializer() {
        gson = new Gson();
    }

    public String serialize (Object obj) { // PLEASE FIX
        return gson.toJson(obj); // PLEASE FIX
    }

    public Object deserialize (String str) {
        return gson.fromJson(str, Object.class); // PLEASE FIX
    }
}