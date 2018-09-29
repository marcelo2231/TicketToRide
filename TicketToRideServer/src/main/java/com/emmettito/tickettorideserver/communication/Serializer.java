package com.emmettito.tickettorideserver.communication;

import com.emmettito.models.CommandModels.GameCommandData;
import com.emmettito.models.CommandModels.GameLobbyCommandData;
import com.emmettito.models.CommandModels.UserCommandData;
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

    public GameLobbyCommandData deserializeGameLobby(InputStream str) throws Exception {
        Scanner in = new Scanner(str);
        StringBuilder sb = new StringBuilder();
        try {
            while (in.hasNextLine()) {
                sb.append(in.nextLine());
            }
            return gson.fromJson(sb.toString(), GameLobbyCommandData.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Serializer: Invalid Json input, unable to parse it.");
        }
    }

    public GameCommandData deserializeGame(InputStream str) throws Exception {
        Scanner in = new Scanner(str);
        StringBuilder sb = new StringBuilder();
        try {
            while (in.hasNextLine()) {
                sb.append(in.nextLine());
            }
            return gson.fromJson(sb.toString(), GameCommandData.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Serializer: Invalid Json input, unable to parse it.");
        }
    }

    public UserCommandData deserializeUser(InputStream str) throws Exception {
        Scanner in = new Scanner(str);
        StringBuilder sb = new StringBuilder();
        try {
            while (in.hasNextLine()) {
                sb.append(in.nextLine());
            }
            return gson.fromJson(sb.toString(), UserCommandData.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Serializer: Invalid Json input, unable to parse it.");
        }
    }
}